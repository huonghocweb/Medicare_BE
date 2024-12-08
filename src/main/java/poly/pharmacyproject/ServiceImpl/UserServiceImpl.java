package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poly.pharmacyproject.Mapper.UserMapper;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Request.UserRequest;
import poly.pharmacyproject.Model.Response.UserResponse;
import poly.pharmacyproject.Repo.RoleRepo;
import poly.pharmacyproject.Repo.UserRepo;
import poly.pharmacyproject.Service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Page<UserResponse> getAllUser(Pageable pageable) {
    Page<User> usersPage = userRepo.findAll(pageable);
        if  (pageable.getPageNumber()>= (usersPage.getTotalPages() -1) ){
            pageable = PageRequest.of(usersPage.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
    List<UserResponse> userResponses = usersPage.getContent().stream()
            .map(userMapper::convertEnToRes)
            .collect(Collectors.toList());
    return new PageImpl<>(userResponses, pageable, usersPage.getTotalElements());
    }


    @Override
    public Optional<UserResponse> getUserById(Integer id) {
    Optional<User> user = userRepo.findById(id);
    return Optional.of(user.map(userMapper::convertEnToRes)
            .orElseThrow(() -> {
                return new EntityNotFoundException("Not Found");
            }));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.convertReqToEn(userRequest);
        // Mã hóa mật khẩu trước khi lưu
        user.setRoles(userRequest.getRoleIds() != null ?
                userRequest.getRoleIds().stream()
                        .map(roleId -> roleRepo.findById(roleId)
                                .orElseThrow(()-> new EntityNotFoundException("not found Role")))
                        .collect(Collectors.toList()) : null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userUpdate = userRepo.save(user);
        return userMapper.convertEnToRes(userUpdate);
    }
    
    @Override
    public Optional<UserResponse> updateUser(UserRequest userRequest, Integer id) {
        return Optional.of(userRepo.findById(id).map(us -> {
            User user = userMapper.convertReqToEn(userRequest);
            user.setRoles(userRequest.getRoleIds() != null ?
                    userRequest.getRoleIds().stream()
                            .map(roleId -> roleRepo.findById(roleId)
                                    .orElseThrow(()-> new EntityNotFoundException("not found Role")))
                            .collect(Collectors.toList()) : null);
            user.setUserId(us.getUserId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User userUpdate = userRepo.save(user);
            return userMapper.convertEnToRes(userUpdate);
        }).orElseThrow(() ->new EntityNotFoundException("Not Found User By Id") ));
    }
    
    @Override
    public Optional<Void> deleteUserById(Integer id) {
        Optional<User> user = userRepo.findById(id);
        user.ifPresent(value -> userRepo.save(value));
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<UserResponse> getUserByUserName(String username) {
        User user = userRepo.findUserByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("not found User"));
        return Optional.of(userMapper.convertEnToRes(user));
    }
}
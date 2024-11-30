package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityNotFoundException;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Request.UserRequest;
import poly.pharmacyproject.Model.Response.UserResponse;
import poly.pharmacyproject.Repo.RoleRepo;
import poly.pharmacyproject.Repo.UserRepo;

@Mapper(componentModel = "spring" , uses= {
        RoleMapper.class
})
public interface UserMapper {

    UserResponse convertEnToRes(User user);

    User convertReqToEn(UserRequest userRequest);
//    public UserResponse convertEnToRes(User user){
//        return UserResponse.builder()
//                .userId(user.getUserId())
//                .userName(user.getUserName())
//                .fullName(user.getFullName())
//                .address(user.getAddress())
//                .phoneNumber(user.getPhoneNumber())
//                .gender(user.getGender())
//                .birthday(user.getBirthday())
//                .status(user.getStatus())
//                .email(user.getEmail())
//                .imageUrl(user.getImageUrl())
//                .password(user.getPassword())
//                .roles(user.getRoles() != null ? user.getRoles().stream()
//                        .map(role -> roleMapper.convertEnToRes(role))
//                        .toList() : null)
//                .build();
//    }
//
//    public User convertReqToEn(UserRequest userRequest) {
//        return User.builder()
//                .userName(userRequest.getUserName())
//                .fullName(userRequest.getFullName())
//                .email(userRequest.getEmail())
//                .birthday(userRequest.getBirthday())
//                .gender(userRequest.getGender())
//                .address(userRequest.getAddress())
//                .password(userRequest.getPassword())
//                .imageUrl(userRequest.getImageUrl())
//                .phoneNumber(userRequest.getPhoneNumber())
//                .status(true)
//                .roles(userRequest.getRoleIds() != null ? userRequest.getRoleIds().stream()
//                        .map(roleId -> roleRepo.findById(roleId)
//                                .orElseThrow(() -> new EntityNotFoundException("Role not found for ID: " + roleId)))
//                        .toList() : null)
//                .build();
//    }

}

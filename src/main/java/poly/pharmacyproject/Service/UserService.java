package poly.pharmacyproject.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Entity.User;
import poly.pharmacyproject.Model.Request.UserRequest;
import poly.pharmacyproject.Model.Response.UserResponse;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Page<UserResponse> getAllUser(Pageable pageable);
    Optional<UserResponse> getUserById(Integer id);
    UserResponse createUser(UserRequest userRequest);
    Optional<UserResponse> updateUser(UserRequest userRequest, Integer id);
    Optional<Void> deleteUserById(Integer id);
    Optional<UserResponse> getUserByUsername(String username);
}
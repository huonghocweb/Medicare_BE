package poly.pharmacyproject.Controller.Api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Config.OAuth2LoginSuccessHandler;
import poly.pharmacyproject.Model.Request.AuthRequest;
import poly.pharmacyproject.Utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginApi {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @GetMapping("/login/oauth2/success")
        public ResponseEntity<?> loginOauth2(
            HttpServletRequest request,
            Authentication authentication
    ) {
        Map<String,Object> result= new HashMap<>();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        System.out.println("Mail" + email);
        try {
            result.put("success",true);
            result.put("message","Get Token");
         //   result.put("data" ,authService.createAuthResponseOAuth2(userName,roles, jwtToken));
        }catch(Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

//
//    @GetMapping("/api/authenticate/oauth2")
//    public ResponseEntity<?> getUserDetails(OAuth2AuthenticationToken authentication) {
//        String userName =authentication.getName();
//        List<String> roles = authentication.getAuthorities().stream()
//                .map(auth -> auth.getAuthority())
//                .toList();
//        String jwtToken = jwtUtils.generateTokenWithRoles(userName,roles);
//        Map<String,Object> result= new HashMap<>();
//        System.out.println("JWT TOKEN" + authService.createAuthResponseOAuth2(userName,roles, jwtToken));
//        try {
//            result.put("success",true);
//            result.put("message","Get Token");
//            result.put("data" ,authService.createAuthResponseOAuth2(userName,roles, jwtToken));
//        }catch(Exception e){
//            result.put("success",false);
//            result.put("message",e.getMessage());
//            result.put("data",null);
//        }
//        return ResponseEntity.ok(result);
//    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<Object > authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        //authenticationManager.authenticate nhận vào đối tướng chứa thông tin người dùng (ở đây là UsernamePasswordAuthenticationToken)
        // sau đó dùng AuthenticationProvider để xác thực thông tin với database thông qua UserDetailServiceImpl mà bạn cấu hình
//        System.out.println(authRequest.getUsername() + authRequest.getPassword());
//        System.out.println(userService.getUserByUsername(authRequest.getUsername()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        }catch (AuthenticationException e){
            throw  new Exception("Incorrect username or password",e);
        }
        // nếu xác thực thành công, lấy thông tin người dùng bằng hàm loadUserByUserName trong UserDetailServiceImpl
        final UserDetails userDetails= userDetailsService.loadUserByUsername(authRequest.getUserName());
        // Lúc này dùng userDetails có các thông tin ng dùng vào utils để tạp token
        final  String jwtToken= jwtUtils.generateToken(userDetails);
        Map<String,Object> result= new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","Get Token");
            result.put("data" ,jwtToken);
        }catch(Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }
}
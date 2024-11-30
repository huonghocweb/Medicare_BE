package poly.pharmacyproject.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.RoleService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/role")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @GetMapping("getAll")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getAllRole() {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get All Role");
            result.put("data",roleService.getAllRole());
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("getById/{roleId}")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF', 'USER')")
    public ResponseEntity<Object> getRoleById(
            @PathVariable("roleId") Integer roleId
    ) {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get Role By Id");
            result.put("data",roleService.getRoleById(roleId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data",null);
        }
        return ResponseEntity.ok(result);
    }

}

package poly.pharmacyproject.Controller.Api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.pharmacyproject.Service.ShipService;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/ship")
public class ShipApi {

    @Autowired
    private ShipService shipService;

    @PostMapping("/getProvince")
    public ResponseEntity<Object> getProvince(){
        Map<String,Object> result = new HashMap<>();
        System.out.println("GET PROVINCE");
        try {
            result.put("success",true);
            result.put("message","get Province");
            result.put("data", shipService.getProvince());
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getDistrict/{provinceId}")
    public ResponseEntity<Object> getDistrictByProvinceId(
            @PathVariable("provinceId") Integer provinceId
    )  {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get District");
            result.put("data", shipService.getDistrict(provinceId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getWard/{districtId}")
    public ResponseEntity<Object> getWard(
            @PathVariable("districtId") Integer districtId
    ) throws JsonProcessingException {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get Ward");
            result.put("data", shipService.getWard(districtId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getShipService/{districtId}")
    public ResponseEntity<Object> getShipService(
            @PathVariable("districtId") Integer districtId
    ) throws JsonProcessingException {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("success",true);
            result.put("message","get Ship Service");
            result.put("data", shipService.getService(districtId));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/getFee/{districtId}/{serviceId}/{wardCode}")
    public ResponseEntity<Object> calculateFee(
            @PathVariable("districtId") Integer districtId,
            @PathVariable("serviceId") Integer serviceId ,
            @PathVariable("wardCode") String wardCode
    ) throws JsonProcessingException {
        Map<String,Object> result = new HashMap<>();
        System.out.println("FEE");
        System.out.println("Calculate Fee" + shipService.calculateFee(serviceId,districtId , wardCode));
        try {
            result.put("success",true);
            result.put("message","Calculate Fee");
            result.put("data", shipService.calculateFee(serviceId,districtId , wardCode));
        }catch (Exception e){
            result.put("success",false);
            result.put("message",e.getMessage());
            result.put("data", null);
        }
        return ResponseEntity.ok(result);
    }
}

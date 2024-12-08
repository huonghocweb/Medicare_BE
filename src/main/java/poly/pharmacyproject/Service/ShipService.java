package poly.pharmacyproject.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public interface ShipService {

    String getProvince();
    String getDistrict(Integer provinceId) throws JsonProcessingException;
    String getWard(Integer districtId) throws JsonProcessingException;
    String getService(Integer toDistrictId) throws JsonProcessingException;
    Object calculateFee(Integer serviceId , Integer toDistrictId , String wardId) throws JsonProcessingException;
}

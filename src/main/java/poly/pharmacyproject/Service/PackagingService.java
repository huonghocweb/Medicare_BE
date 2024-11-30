package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.PackagingResponse;

import java.util.List;

@Service
public interface PackagingService {

    List<PackagingResponse> getAllPackaging();
    PackagingResponse getPackagingById(Integer packagingId);
    List<PackagingResponse> getPackagingsByProductId(Integer productId);
}

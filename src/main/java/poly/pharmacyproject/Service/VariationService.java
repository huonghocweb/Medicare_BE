package poly.pharmacyproject.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Request.VariationRequest;
import poly.pharmacyproject.Model.Response.VariationResponse;

import java.util.List;

@Service
public interface VariationService {
    Page<VariationResponse> getAllVariation(Pageable pageable);
    VariationResponse getVariationById(Integer variationId);
    VariationResponse createVariation(VariationRequest variationRequest);
    VariationResponse updateVariation(Integer variationId, VariationRequest variationRequest);
    List<VariationResponse> getVariationByPackagingId(Integer productId,Integer packagingId);
    List<VariationResponse> getVariationByDosageForm(Integer productId,Integer dosageFormId);
    VariationResponse getVariationByPackagingIdAndDosageFormId(Integer productId,Integer packagingId, Integer dosageFormId);

}

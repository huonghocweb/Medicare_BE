package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.VariationMapper;
import poly.pharmacyproject.Model.Entity.Variation;
import poly.pharmacyproject.Model.Request.VariationRequest;
import poly.pharmacyproject.Model.Response.VariationResponse;
import poly.pharmacyproject.Repo.VariationRepo;
import poly.pharmacyproject.Service.VariationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariationServiceImpl implements VariationService {
    @Autowired
    private VariationRepo variationRepo;
    @Autowired
    private VariationMapper variationMapper;
    @Override
    public Page<VariationResponse> getAllVariation(Pageable pageable) {
        Page<Variation> variationPages =variationRepo.findAll(pageable);
        if  (pageable.getPageNumber()>= (variationPages.getTotalPages() -1) ){
            pageable = PageRequest.of(variationPages.getTotalPages()-1, pageable.getPageSize(), pageable.getSort());
        }
        List<VariationResponse> variationResponses = variationPages.getContent().stream()
                .map(variationMapper :: convertEnToRes)
                .collect(Collectors.toList());
        return new PageImpl<>(variationResponses,pageable, variationPages.getTotalElements());
    }

    @Override
    public VariationResponse getVariationById(Integer variationId) {
        Variation variation = variationRepo.findById(variationId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Variation"));
        return variationMapper.convertEnToRes(variation);
    }

    @Override
    public VariationResponse createVariation(VariationRequest variationRequest) {
        return null;
    }

    @Override
    public VariationResponse updateVariation(Integer variationId, VariationRequest variationRequest) {
        return null;
    }

    @Override
    public List<VariationResponse> getVariationByPackagingId(Integer productId,Integer packagingId) {
        List<Variation> variations = variationRepo.getVariationByPackagingId(packagingId,productId);
        return variations.stream()
                .map(variationMapper :: convertEnToRes)
                .toList();
    }

    @Override
    public List<VariationResponse> getVariationByDosageForm(Integer productId,Integer dosageFormId) {
        List<Variation> variations = variationRepo.getVariationByDosageFormId(dosageFormId,productId);
        return variations.stream()
                .map(variationMapper :: convertEnToRes)
                .toList();
    }

    @Override
    public VariationResponse getVariationByPackagingIdAndDosageFormId(Integer productId,Integer packagingId, Integer dosageFormId) {
        Variation variation = variationRepo.getVariationByPackagingIdAndDosageFormId(dosageFormId, packagingId, productId);
        return variationMapper.convertEnToRes(variation);
    }
}

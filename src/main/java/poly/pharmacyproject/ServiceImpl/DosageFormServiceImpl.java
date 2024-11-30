package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.DosageFormMapper;
import poly.pharmacyproject.Model.Entity.DosageForm;
import poly.pharmacyproject.Model.Response.DosageFormResponse;
import poly.pharmacyproject.Repo.DosageFormRepo;
import poly.pharmacyproject.Service.DosageFormService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DosageFormServiceImpl implements DosageFormService {

    @Autowired
    private DosageFormRepo dosageFormRepo;
    @Autowired
    private DosageFormMapper dosageFormMapper;
    @Override
    public List<DosageFormResponse> getAllDosageForm() {
        List<DosageForm> dosageForms = dosageFormRepo.findAll();
        return dosageForms.stream()
                .map(dosageFormMapper :: convertEnToRes)
                .collect(Collectors.toList());
    }

    @Override
    public DosageFormResponse getDosageFormId(Integer dosageFormId) {
        DosageForm dosageForm = dosageFormRepo.findById(dosageFormId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Dosage Form "));
        return dosageFormMapper.convertEnToRes(dosageForm);
    }

    @Override
    public List<DosageFormResponse> getDosageFormsByProductId(Integer productId) {
        List<DosageForm> dosageForms = dosageFormRepo.getDosageFormsByProductId(productId);
        return dosageForms.stream()
                .map(dosageFormMapper :: convertEnToRes)
                .toList();
    }
}

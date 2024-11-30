package poly.pharmacyproject.Service;

import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Entity.DosageForm;
import poly.pharmacyproject.Model.Response.DosageFormResponse;
import poly.pharmacyproject.Model.Response.PackagingResponse;

import java.util.List;

@Service
public interface DosageFormService {
    List<DosageFormResponse> getAllDosageForm();
    DosageFormResponse getDosageFormId(Integer dosageFormId);
    List<DosageFormResponse> getDosageFormsByProductId(Integer productId);
}

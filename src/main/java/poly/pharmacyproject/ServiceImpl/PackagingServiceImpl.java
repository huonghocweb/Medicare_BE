package poly.pharmacyproject.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Mapper.PackagingMapper;
import poly.pharmacyproject.Model.Entity.Packaging;
import poly.pharmacyproject.Model.Response.PackagingResponse;
import poly.pharmacyproject.Repo.PackagingRepo;
import poly.pharmacyproject.Service.PackagingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackagingServiceImpl implements PackagingService {
    @Autowired
    private PackagingRepo packagingRepo;
    @Autowired
    private PackagingMapper packagingMapper;
    @Override
    public List<PackagingResponse> getAllPackaging() {
        List<Packaging> packagings = packagingRepo.findAll();
        return packagings.stream()
                .map(packagingMapper :: convertEnToRes)
                .collect(Collectors.toList());
    }

    @Override
    public PackagingResponse getPackagingById(Integer packagingId) {
        Packaging packaging = packagingRepo.findById(packagingId)
                .orElseThrow(() -> new EntityNotFoundException("Not found Packaging"));;
        return packagingMapper.convertEnToRes(packaging);
    }

    @Override
    public List<PackagingResponse> getPackagingsByProductId(Integer productId) {
        List<Packaging> packagings = packagingRepo.getPackagingsdByProductId(productId);
        return packagings.stream()
                .map(packagingMapper :: convertEnToRes)
                .toList();
    }
}

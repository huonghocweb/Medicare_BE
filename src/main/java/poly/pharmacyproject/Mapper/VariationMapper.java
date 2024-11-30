package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import poly.pharmacyproject.Model.Entity.PriceHistory;
import poly.pharmacyproject.Model.Entity.StockMovement;
import poly.pharmacyproject.Model.Entity.Variation;
import poly.pharmacyproject.Model.Request.VariationRequest;
import poly.pharmacyproject.Model.Response.VariationResponse;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = {
        DosageFormMapper.class,
        PackagingMapper.class,
        ProductMapper.class,
        PriceHistoryMapper.class,
        StockMovementMapper.class
})
public interface VariationMapper {
        VariationResponse convertEnToRes(Variation variation);

        Variation convertReqToEn(VariationRequest variationRequest);
}

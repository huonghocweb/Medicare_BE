package poly.pharmacyproject.Mapper;

import org.mapstruct.Mapper;
import poly.pharmacyproject.Model.Entity.PriceHistory;
import poly.pharmacyproject.Model.Response.PriceHistoryResponse;

@Mapper(componentModel = "spring")
public interface PriceHistoryMapper {
    PriceHistoryResponse convertEnToRes(PriceHistory priceHistory);
}

package poly.pharmacyproject.Model.Enum;

import lombok.Getter;

@Getter
public enum CommonOrderStatus {
    PENDING_PAYMENT(1),
    PROCESSING(2),
    SHIPPING(3),
    DELIVERED(4),
    RETURN_REQUEST(5),
    COMPLETE(6),
    CANCEL(7),
    RETURNED(8);

    private final int id;

    CommonOrderStatus(int id){
        this.id = id;
    }

    public static CommonOrderStatus fromString(String status) {
        switch (status.toUpperCase()) {
            case "PENDING_PAYMENT":
                return PENDING_PAYMENT;
            case "PROCESSING":
                return PROCESSING;
            case "SHIPPING":
                return SHIPPING;
            case "DELIVERED":
                return DELIVERED;
            case "RETURN_REQUEST":
                return RETURN_REQUEST;
            case "COMPLETE":
                return COMPLETE;
            case "CANCEL":
                return CANCEL;
            case "RETURNED":
                return RETURNED;
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }

}

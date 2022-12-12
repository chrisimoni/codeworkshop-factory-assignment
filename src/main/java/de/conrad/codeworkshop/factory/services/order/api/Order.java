package de.conrad.codeworkshop.factory.services.order.api;

import java.math.BigDecimal;
import java.util.List;

import static de.conrad.codeworkshop.factory.services.order.api.OrderStatus.*;

/**
 * @author Andreas Hartmann
 */
public class Order {
    private List<Position> positions;
    private OrderConfirmation orderConfirmation;
    private OrderStatus status = PENDING;

    public void validate() {
        if (!positions.isEmpty() && status == PENDING) {
            //decline order if any item fail validation
            boolean result = positions.stream().allMatch(x -> validateDigitLength(x.getProductId())
                    && validateQuantity(x.getQuantity()));
            status = result ? ACCEPTED : DECLINED;
        } else {
            status = DECLINED;
        }
    }

    public void setOrderConfirmation(final OrderConfirmation orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    private boolean validateDigitLength(int number) {
        int length = (int) (Math.log10(number) + 1);
        return length >= 6 && length <= 9;
    }

    private boolean validateQuantity(BigDecimal quantity) {
        double qty = quantity.doubleValue();
        if(qty % 10 == 0 || qty >= 1 || qty == 42.42) {
            return true;
        }

        return false;
    }
}

package de.conrad.codeworkshop.factory.services.order.api;

import java.math.BigDecimal;

/**
 * @author Andreas Hartmann
 */
public class Position {
    private Integer productId;
    private BigDecimal quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}

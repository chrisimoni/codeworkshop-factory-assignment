package de.conrad.codeworkshop.factory.services.order.api;

/**
 * @author Andreas Hartmann
 */
public enum OrderStatus {

    /**
     * Order has been accepted and will go into manufacturing soon.
     */
    ACCEPTED,

    /**
     * The order has been declined.
     */
    DECLINED,

    /**
     * Order has arrived, but not yet been accepted or declined.
     */
    PENDING,

    /**
     * The order is currently in manufacturing or about to be shipped.
     */
    IN_PROGRESS,

    /**
     * Order has been processed and shipped to the customer.
     */
    COMPLETED
}

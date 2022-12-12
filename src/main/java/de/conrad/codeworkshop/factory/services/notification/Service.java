package de.conrad.codeworkshop.factory.services.notification;

import de.conrad.codeworkshop.factory.services.order.api.Order;

/**
 * @author Andreas Hartmann
 */
@org.springframework.stereotype.Service("notificationService")
public class Service {

    public void notifyCustomer(final Order order) {
        // Dummy function that would notify the customer that manufacturing is completed.
        try {
            Thread.sleep(500);
        } catch (final InterruptedException interruptedException) {
            System.err.println(interruptedException.getMessage());
            // Do nothing
        }
    }
}

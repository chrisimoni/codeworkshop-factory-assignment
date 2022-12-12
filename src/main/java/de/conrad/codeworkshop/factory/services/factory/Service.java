package de.conrad.codeworkshop.factory.services.factory;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Andreas Hartmann
 */
@org.springframework.stereotype.Service("factoryService")
class Service {
    private final de.conrad.codeworkshop.factory.services.notification.Service notificationService;

    public Service(de.conrad.codeworkshop.factory.services.notification.Service notificationService) {
        this.notificationService = notificationService;

    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private ConcurrentLinkedQueue<Order> manufacturingQueue = new ConcurrentLinkedQueue<>();

    void enqueue(final Order order) {
        order.setStatus(OrderStatus.IN_PROGRESS);
        manufacturingQueue.add(order);
    }

    void dequeue(final Order order) {
        manufacturingQueue.remove(order);
    }

    @Scheduled(cron = "0 * * * * *") //run every 1 minute
    void processOrder() throws InterruptedException {
        if(manufacturingQueue.isEmpty()) return;

        for (Order order: manufacturingQueue) {
            //mark order as completed and send notification
            order.setStatus(OrderStatus.COMPLETED);
            Thread.sleep(5000);
            notificationService.notifyCustomer(order);

            //remove order from queue
            dequeue(order);
        }
    }
}

package de.conrad.codeworkshop.factory.services.order;

import de.conrad.codeworkshop.factory.services.order.api.Order;
import de.conrad.codeworkshop.factory.services.order.api.OrderConfirmation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andreas Hartmann
 */
@RestController("orderController")
@RequestMapping("/order")
public class Controller {

    private final Service factoryService;

    @Autowired
    public Controller(final Service service) {
        this.factoryService = service;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public OrderConfirmation createOrder(final Order order) {
        return factoryService.createOrder(order);
    }
}
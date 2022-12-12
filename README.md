This application represents a factory. Customers can create orders using the OrderController's create method. The order 
is then validated. If it is valid, its status will be set to ACCEPTED and it will get an OrderConfirmation and be put on a queue via the FactoryController. Otherwise it will get a BLANK_ORDER_CONFIRMATION.

There is also a notification service, that would then send a message to the customer once the order has been completed.

There are three open tasks for this project. Please work on them in this order and try to get as far as possible.

1. Extend the order validation so that Orders are only valid if the productId has between 6 and 9 digits (including) and the quantity is either 
* divisible by 10
* or less than one and larger than 0
* or exactly 42.42.
2. Adjust de.conrad.codeworkshop.factory.services.order.Controller so that it accepts and produces JSON.
3. Build a primitive asynchronous worker that will remove entries from de.conrad.codeworkshop.factory.services.factory.Service#manufacturingQueue, sets their status to COMPLETED, waits for five seconds and then calls de.conrad.codeworkshop.factory.services.notification.Service#notifyCustomer (to notify the customer that their order is completed).

Please be ready to present, demonstrate and send in your results.

### Solution
1. I created extended the validation method in Order.java by creating validateDigitLength and validateQuantity methods:
```java
//validate method
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

//validate digit length
private boolean validateDigitLength(int number) {
    int length = (int) (Math.log10(number) + 1);
    return length >= 6 && length <= 9;
}

//validate quantity
private boolean validateQuantity(BigDecimal quantity) {
    double qty = quantity.doubleValue();
    if(qty % 10 == 0 || qty >= 1 || qty == 42.42) {
        return true;
    }

    return false;
}

```

2. I adjusted the PostMapping annotation in order controller with the following to accept and produce JSON:
```java
@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public OrderConfirmation createOrder(final Order order) {
    return factoryService.createOrder(order);
}
```

3. To implement the third task i did the following:
```java
//FactoryApplication.java
@SpringBootApplication
@EnableScheduling //enabled scheduling
public class FactoryApplication {
}

//Factory -- Service
    
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
```

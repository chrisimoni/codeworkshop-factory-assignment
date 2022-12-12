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

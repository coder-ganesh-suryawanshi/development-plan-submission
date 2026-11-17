
package org.example.junit5.service;

import org.example.junit5.model.Invoice;
import org.example.junit5.model.Order;
import org.example.PaymentProcessor;

public class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final BillingService billingService;

    public OrderService(PaymentProcessor paymentProcessor, BillingService billingService) {
        this.paymentProcessor = paymentProcessor;
        this.billingService = billingService;
    }

    public boolean completeOrder(Order order) {
        boolean paid = paymentProcessor.processPayment(order.getId(), order.getAmount());
        if (paid) {
            billingService.generateInvoice(new Invoice(order.getId(), order.getAmount()));
            return true;
        }
        return false;
    }
}

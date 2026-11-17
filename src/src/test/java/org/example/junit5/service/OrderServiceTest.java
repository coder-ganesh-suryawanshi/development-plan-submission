package org.example.junit5.service;

import org.example.PaymentProcessor;
import org.example.junit5.model.Invoice;
import org.example.junit5.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PaymentProcessor paymentProcessor;

    @Mock
    private BillingService billingService;

    @InjectMocks
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<Invoice> invoiceCaptor;

    @Test
    void testCompleteOrder_Success() {
        // Arrange
        Order order = new Order("ORD-1001", 150.00);
        Mockito.when(paymentProcessor.processPayment("ORD-1001", 150.00)).thenReturn(true);

        // Act
        boolean result = orderService.completeOrder(order);

        // Assert
        Assertions.assertTrue(result);
        Mockito.verify(paymentProcessor, Mockito.times(1)).processPayment("ORD-1001", 150.00);

        // Capture and verify argument passed to billing service
        Mockito.verify(billingService).generateInvoice(invoiceCaptor.capture());
        Invoice capturedInvoice = invoiceCaptor.getValue();
        Assertions.assertEquals(150.00, capturedInvoice.getTotalAmount());
    }

    @Test
    void testCompleteOrder_PaymentFailed() {
        // Arrange
        Order order = new Order("ORD-1002", 75.50);
        Mockito.when(paymentProcessor.processPayment(ArgumentMatchers.anyString(), ArgumentMatchers.anyDouble())).thenReturn(false);

        // Act
        boolean result = orderService.completeOrder(order);

        // Assert
        Assertions.assertFalse(result);
        Mockito.verify(billingService, Mockito.never()).generateInvoice(ArgumentMatchers.any(Invoice.class));
    }
}
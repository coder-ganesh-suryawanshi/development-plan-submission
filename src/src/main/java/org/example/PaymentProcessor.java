package org.example;

public interface PaymentProcessor { boolean processPayment(String orderId, double amount); }
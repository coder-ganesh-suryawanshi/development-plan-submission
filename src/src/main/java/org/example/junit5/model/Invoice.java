package org.example.junit5.model;

public class Invoice {
    private String orderId;
    private double totalAmount;
    public Invoice(String orderId, double totalAmount) { this.orderId = orderId; this.totalAmount = totalAmount; }
    public double getTotalAmount() { return totalAmount; }
}

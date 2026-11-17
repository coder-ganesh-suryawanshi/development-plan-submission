package org.example.junit5_Advanced_Annotation.model;

public class LoanResult {
    private String applicationId;
    private LoanStatus status;
    private double approvedAmount;
    private double interestRate;
    private String reason;

    public LoanResult(String applicationId, LoanStatus status, double approvedAmount, double interestRate, String reason) {
        this.applicationId = applicationId;
        this.status = status;
        this.approvedAmount = approvedAmount;
        this.interestRate = interestRate;
        this.reason = reason;
    }

    public String getApplicationId() { return applicationId; }
    public LoanStatus getStatus() { return status; }
    public double getApprovedAmount() { return approvedAmount; }
    public double getInterestRate() { return interestRate; }
    public String getReason() { return reason; }
}
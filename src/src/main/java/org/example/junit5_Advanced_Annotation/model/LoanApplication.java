package org.example.junit5_Advanced_Annotation.model;

public class LoanApplication {
    private String applicantId;
    private double monthlyIncome;
    private double requestedAmount;
    private int termMonths;
    private int creditScore;
    private CustomerTier customerTier;

    public LoanApplication(String applicantId, double monthlyIncome, double requestedAmount, int termMonths, int creditScore, CustomerTier customerTier) {
        this.applicantId = applicantId;
        this.monthlyIncome = monthlyIncome;
        this.requestedAmount = requestedAmount;
        this.termMonths = termMonths;
        this.creditScore = creditScore;
        this.customerTier = customerTier;
    }

    public String getApplicantId() { return applicantId; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public double getRequestedAmount() { return requestedAmount; }
    public int getTermMonths() { return termMonths; }
    public int getCreditScore() { return creditScore; }
    public CustomerTier getCustomerTier() { return customerTier; }
}
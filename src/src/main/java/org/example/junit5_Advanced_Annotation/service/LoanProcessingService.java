
package org.example.junit5_Advanced_Annotation.service;

import org.example.junit5_Advanced_Annotation.exception.InvalidLoanRequestException;
import org.example.junit5_Advanced_Annotation.model.CustomerTier;
import org.example.junit5_Advanced_Annotation.model.LoanApplication;
import org.example.junit5_Advanced_Annotation.model.LoanResult;
import org.example.junit5_Advanced_Annotation.model.LoanStatus;

public class LoanProcessingService {

    public LoanResult evaluateLoan(LoanApplication application) {
        // Validation rule
        if (application.getRequestedAmount() <= 0 || application.getMonthlyIncome() <= 0) {
            throw new InvalidLoanRequestException("Income and requested amount must be greater than zero.");
        }

        // Automatic Rejection for poor credit score
        if (application.getCreditScore() < 580) {
            return new LoanResult(application.getApplicantId(), LoanStatus.REJECTED, 0, 0.0, "Credit score below minimum threshold.");
        }

        // Calculate Debt-to-Income (DTI) rough monthly payment estimate
        double estimatedMonthlyPayment = application.getRequestedAmount() / application.getTermMonths();
        double dtiRatio = estimatedMonthlyPayment / application.getMonthlyIncome();

        // VIP / Tier bonuses
        double baseInterestRate = 7.5;
        if (application.getCustomerTier() == CustomerTier.GOLD || application.getCustomerTier() == CustomerTier.PLATINUM) {
            baseInterestRate -= 1.5;
        }

        // Evaluation Logic
        if (application.getCreditScore() >= 750 && dtiRatio <= 0.40) {
            return new LoanResult(application.getApplicantId(), LoanStatus.APPROVED, application.getRequestedAmount(), baseInterestRate, "Low risk, auto-approved.");
        } else if (dtiRatio > 0.60 || application.getCreditScore() < 620) {
            return new LoanResult(application.getApplicantId(), LoanStatus.REJECTED, 0, 0.0, "High DTI ratio or subprime credit score.");
        } else {
            return new LoanResult(application.getApplicantId(), LoanStatus.MANUAL_REVIEW, application.getRequestedAmount() * 0.8, baseInterestRate + 1.0, "Requires underwriter review.");
        }
    }
}
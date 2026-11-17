package org.example.junit5_Advanced_Annotation.service;

import org.example.junit5_Advanced_Annotation.exception.InvalidLoanRequestException;
import org.example.junit5_Advanced_Annotation.model.LoanApplication;
import org.example.junit5_Advanced_Annotation.model.LoanStatus;
import org.example.junit5_Advanced_Annotation.model.LoanResult;
import org.example.junit5_Advanced_Annotation.model.CustomerTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Loan Processing Service - Core Behavior Tests")
class LoanProcessingNestedTest {

    private LoanProcessingService service;

    @BeforeEach
    void setUp() {
        service = new LoanProcessingService();
    }

    @Nested
    @DisplayName("Given valid application inputs")
    class ValidApplications {

        @Test
        @DisplayName("Should approve low-risk, high-credit applicants")
        void testApproveLowRisk() {
            LoanApplication app = new LoanApplication("A-1", 10000.0, 20000.0, 24, 800, CustomerTier.GOLD);
            LoanResult result = service.evaluateLoan(app);

            assertEquals(LoanStatus.APPROVED, result.getStatus());
            assertEquals(20000.0, result.getApprovedAmount());
        }

        @Test
        @DisplayName("Should flag moderate risk for manual review")
        void testManualReview() {
            LoanApplication app = new LoanApplication("A-2", 4000.0, 25000.0, 12, 650, CustomerTier.STANDARD);
            LoanResult result = service.evaluateLoan(app);

            assertEquals(LoanStatus.MANUAL_REVIEW, result.getStatus());
        }
    }

    @Nested
    @DisplayName("Given invalid or high-risk inputs")
    class InvalidOrRiskyApplications {

        @Test
        @DisplayName("Should reject applicants with credit score below 580")
        void testRejectLowCreditScore() {
            LoanApplication app = new LoanApplication("A-3", 7000.0, 15000.0, 12, 530, CustomerTier.SILVER);
            LoanResult result = service.evaluateLoan(app);

            assertEquals(LoanStatus.REJECTED, result.getStatus());
        }

        @Test
        @DisplayName("Should throw exception when requested amount is zero or negative")
        void testInvalidAmountThrowsException() {
            LoanApplication app = new LoanApplication("A-4", 5000.0, 0.0, 12, 700, CustomerTier.STANDARD);

            assertThrows(InvalidLoanRequestException.class, () -> service.evaluateLoan(app));
        }
    }
}
package org.example.junit5_Advanced_Annotation.service;

import org.example.junit5_Advanced_Annotation.model.*;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanProcessingDynamicTest {

    private final LoanProcessingService service = new LoanProcessingService();

    @TestFactory
    Stream<DynamicTest> dynamicTestCreditScoreThresholds() {
        record TestCase(int creditScore, LoanStatus expectedStatus, String description) {}

        List<TestCase> testCases = List.of(
            new TestCase(550, LoanStatus.REJECTED, "Subprime credit should always reject"),
            new TestCase(780, LoanStatus.APPROVED, "Prime credit with low DTI should approve")
        );

        return testCases.stream().map(tc -> 
            DynamicTest.dynamicTest(tc.description(), () -> {
                LoanApplication app = new LoanApplication("DYN-1", 9000.0, 10000.0, 12, tc.creditScore(), CustomerTier.STANDARD);
                LoanResult result = service.evaluateLoan(app);
                assertEquals(tc.expectedStatus, result.getStatus());
            })
        );
    }
}
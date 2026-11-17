package org.example.junit5_Advanced_Annotation.service;

import org.example.junit5_Advanced_Annotation.model.LoanApplication;
import org.example.junit5_Advanced_Annotation.model.LoanResult;
import org.example.junit5_Advanced_Annotation.model.LoanStatus;
import org.example.junit5_Advanced_Annotation.provider.LoanApplicationArgumentsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoanProcessingArgumentsSourceTest {

    @ParameterizedTest(name = "Test {index} => Application {0} evaluates to status {1}")
    @ArgumentsSource(LoanApplicationArgumentsProvider.class)
    @DisplayName("Parameterized evaluation using external ArgumentsProvider")
    void testLoanEvaluationWithCustomProvider(LoanApplication application, LoanStatus expectedStatus) {
        LoanProcessingService service = new LoanProcessingService();
        LoanResult result = service.evaluateLoan(application);

        assertEquals(expectedStatus, result.getStatus());
    }
}
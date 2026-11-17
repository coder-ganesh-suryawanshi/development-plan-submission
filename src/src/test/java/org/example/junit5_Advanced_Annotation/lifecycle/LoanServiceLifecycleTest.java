package org.example.junit5_Advanced_Annotation.lifecycle;

import org.example.junit5_Advanced_Annotation.model.CustomerTier;
import org.example.junit5_Advanced_Annotation.model.LoanApplication;
import org.example.junit5_Advanced_Annotation.model.LoanResult;
import org.example.junit5_Advanced_Annotation.service.LoanProcessingService;
import org.example.junit5_Advanced_Annotation.extension.AuditLoggingExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AuditLoggingExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoanServiceLifecycleTest {

    private static final Logger logger = Logger.getLogger(LoanServiceLifecycleTest.class.getName());
    private LoanProcessingService service;

    @BeforeAll
    void beforeAllTests() {
        logger.info("--> @BeforeAll: Initializing Loan Processing Test Suite resources.");
        service = new LoanProcessingService();
    }

    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        logger.info(() -> "--> @BeforeEach: Preparing data for test -> " + testInfo.getDisplayName());
    }

    @Test
    void testLifecycleExecutionOne() {
        LoanApplication app = new LoanApplication("LC-1", 6000.0, 10000.0, 12, 720, CustomerTier.SILVER);
        LoanResult result = service.evaluateLoan(app);
        assertNotNull(result);
    }

    @Test
    void testLifecycleExecutionTwo() {
        LoanApplication app = new LoanApplication("LC-2", 9000.0, 15000.0, 12, 810, CustomerTier.GOLD);
        LoanResult result = service.evaluateLoan(app);
        assertNotNull(result);
    }

    @AfterEach
    void afterEachTest(TestInfo testInfo) {
        logger.info(() -> "--> @AfterEach: Cleaning up after test -> " + testInfo.getDisplayName());
    }

    @AfterAll
    void afterAllTests() {
        logger.info("--> @AfterAll: Teardown test suite resources.");
    }
}
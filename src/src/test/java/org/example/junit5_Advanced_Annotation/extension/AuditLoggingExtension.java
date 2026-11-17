package org.example.junit5_Advanced_Annotation.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

public class AuditLoggingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = Logger.getLogger(AuditLoggingExtension.class.getName());

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        logger.info(() -> "==>[AUDIT START] Executing test: " + context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        logger.info(() -> "==>[AUDIT END] Finished test: " + context.getDisplayName());
    }
}
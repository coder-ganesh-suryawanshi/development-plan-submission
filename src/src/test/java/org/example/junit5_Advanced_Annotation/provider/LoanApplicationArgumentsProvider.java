package org.example.junit5_Advanced_Annotation.provider;

import org.example.junit5_Advanced_Annotation.model.CustomerTier;
import org.example.junit5_Advanced_Annotation.model.LoanApplication;
import org.example.junit5_Advanced_Annotation.model.LoanStatus;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class LoanApplicationArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(new LoanApplication("APP-101", 8000.0, 30000.0, 36, 780, CustomerTier.PLATINUM), LoanStatus.APPROVED),
            Arguments.of(new LoanApplication("APP-102", 3000.0, 50000.0, 12, 550, CustomerTier.STANDARD), LoanStatus.REJECTED),
            Arguments.of(new LoanApplication("APP-103", 4000.0, 25000.0, 24, 680, CustomerTier.SILVER), LoanStatus.MANUAL_REVIEW)
        );
    }
}
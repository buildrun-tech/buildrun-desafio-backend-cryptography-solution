package tech.buildrun.crypto.controller.dto;

import tech.buildrun.crypto.entity.TransactionEntity;

public record TransactionResponse(Long id, String userDocument, String creditCardToken, Long value) {

    public static TransactionResponse fromEntity(TransactionEntity entity) {
        return new TransactionResponse(
                entity.getTransactionId(),
                entity.getPlainTextUserDocument(),
                entity.getPlainTextCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}

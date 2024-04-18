package tech.buildrun.crypto.controller.dto;

public record CreateTransactionRequest(String userDocument, String creditCardToken, Long value) {
}

package tech.buildrun.crypto.entity;

import jakarta.persistence.*;
import tech.buildrun.crypto.service.CryptoService;

@Entity
@Table(name = "tb_transactions")
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    @Column(name = "transaction_value")
    private Long transactionValue;

    @Transient
    private String plainTextUserDocument;

    @Transient
    private String plainTextCreditCardToken;

    public TransactionEntity() {
    }

    public TransactionEntity(String userDocument, String creditCardToken, Long transactionValue) {
        this.plainTextUserDocument = userDocument;
        this.plainTextCreditCardToken = creditCardToken;
        this.transactionValue = transactionValue;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getEncryptedUserDocument() {
        return encryptedUserDocument;
    }

    public void setEncryptedUserDocument(String encryptedUserDocument) {
        this.encryptedUserDocument = encryptedUserDocument;
    }

    public String getEncryptedCreditCardToken() {
        return encryptedCreditCardToken;
    }

    public void setEncryptedCreditCardToken(String encryptedCreditCardToken) {
        this.encryptedCreditCardToken = encryptedCreditCardToken;
    }

    public Long getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(Long transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getPlainTextUserDocument() {
        return plainTextUserDocument;
    }

    public void setPlainTextUserDocument(String plainTextUserDocument) {
        this.plainTextUserDocument = plainTextUserDocument;
    }

    public String getPlainTextCreditCardToken() {
        return plainTextCreditCardToken;
    }

    public void setPlainTextCreditCardToken(String plainTextCreditCardToken) {
        this.plainTextCreditCardToken = plainTextCreditCardToken;
    }

    @PrePersist
    public void prePersist() {
        this.encryptedUserDocument = CryptoService.encrypt(plainTextUserDocument);
        this.encryptedCreditCardToken = CryptoService.encrypt(plainTextCreditCardToken);
    }

    @PostLoad
    public void postLoad() {
        this.plainTextUserDocument = CryptoService.decrypt(encryptedUserDocument);
        this.plainTextCreditCardToken = CryptoService.decrypt(encryptedCreditCardToken);
    }
}

package tech.buildrun.crypto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.buildrun.crypto.controller.dto.CreateTransactionRequest;
import tech.buildrun.crypto.controller.dto.TransactionResponse;
import tech.buildrun.crypto.controller.dto.UpdateTransactionRequest;
import tech.buildrun.crypto.entity.TransactionEntity;
import tech.buildrun.crypto.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void save(CreateTransactionRequest createTransactionRequest) {
        repository.save(
                new TransactionEntity(
                        createTransactionRequest.userDocument(),
                        createTransactionRequest.creditCardToken(),
                        createTransactionRequest.value()
                )
        );
    }

    public void update(Long id, UpdateTransactionRequest updateTransactionRequest) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setTransactionValue(updateTransactionRequest.value());

        repository.save(entity);
    }

    public TransactionResponse findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEntity(entity);
    }

    public Page<TransactionResponse> findAll(int page, int pageSize) {
        var content = repository.findAll(PageRequest.of(page, pageSize));

        return content.map(TransactionResponse::fromEntity);
    }

    public void deleteById(Long id) {
        var exists = repository.existsById(id);

        if (exists) {
            repository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }
}

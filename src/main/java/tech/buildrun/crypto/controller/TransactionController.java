package tech.buildrun.crypto.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.crypto.controller.dto.CreateTransactionRequest;
import tech.buildrun.crypto.controller.dto.TransactionResponse;
import tech.buildrun.crypto.controller.dto.UpdateTransactionRequest;
import tech.buildrun.crypto.service.TransactionService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest createTransactionRequest) {
        transactionService.save(createTransactionRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
                                       @RequestBody UpdateTransactionRequest updateTransactionRequest) {
        transactionService.update(id, updateTransactionRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable("id") Long id) {
        var transaction = transactionService.findById(id);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        var content = transactionService.findAll(page, pageSize);

        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

package tech.buildrun.cryptography.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.cryptography.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}

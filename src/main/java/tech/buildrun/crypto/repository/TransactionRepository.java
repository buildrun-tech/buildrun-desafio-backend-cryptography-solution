package tech.buildrun.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.crypto.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}

package levelup.moneytransfer.repo;

import levelup.moneytransfer.dao.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<TransactionEntity,Integer> {
}

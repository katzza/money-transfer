package levelup.moneytransfer.repo;


import levelup.moneytransfer.dao.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BalanceRepo extends JpaRepository<BalanceEntity,Integer> {
}

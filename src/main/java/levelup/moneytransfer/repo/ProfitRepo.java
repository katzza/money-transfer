package levelup.moneytransfer.repo;

import levelup.moneytransfer.dao.ProfitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfitRepo extends JpaRepository<ProfitEntity,Integer> {
}

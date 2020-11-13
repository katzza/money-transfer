package levelup.moneytransfer.dao;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "profit", schema = "public", catalog = "bank_db")
public class ProfitEntity {

    private int profitId;
    private TransactionEntity transactionId;
    private double amount;

    @Id
    @Column(name = "profit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProfitId() {
        return profitId;
    }

    @Column(name = "transaction_id")
    public void setTransactionId(TransactionEntity transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

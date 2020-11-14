package levelup.moneytransfer.dao;

import javax.persistence.*;


@Entity
@Table(name = "profit", schema = "public", catalog = "bank_db")
public class ProfitEntity {

    private int profitId;
    private double amount;
    private TransactionEntity transactionByTransactionId;

    @Id
    @Column(name = "profit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getProfitId() {
        return profitId;
    }

    public void setProfitId(int profitId) {
        this.profitId = profitId;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Column(name = "transaction")
    @JoinColumn (columnDefinition = "transaction_id")
    public void setTransactionId(TransactionEntity transactionByTransactionId) {
        this.transactionByTransactionId = transactionByTransactionId;
    }

    public TransactionEntity transactionByTransactionId() {
        return transactionByTransactionId;
    }
}

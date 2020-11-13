package levelup.moneytransfer.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "account", schema = "public", catalog = "bank_db")
public class AccountEntity {
    private int accountId;
    private String accNumber;
    private String currencyCode;
    private Integer clientId;
 //   private ClientEntity clientByClientId;
    private Collection<BalanceEntity> balancesByAccountId;

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "acc_number")
    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Basic
    @Column(name = "currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    @Basic
    @Column(name = "client_id")
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return accountId == that.accountId &&
                Objects.equals(accNumber, that.accNumber) &&
                Objects.equals(currencyCode, that.currencyCode);
                //Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accNumber, currencyCode);
    }

 /*  @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")   //в целях моего микросервиса это не нужно и мешает
    public ClientEntity getClientByClientId() {                        //а в Integer хранить это нельзя
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }*/

    @OneToMany(mappedBy = "accountId")
    public Collection<BalanceEntity> getBalancesByAccountId() {
        return balancesByAccountId;
    }

    public void setBalancesByAccountId(Collection<BalanceEntity> balancesByAccountId) {
        this.balancesByAccountId = balancesByAccountId;
    }
}

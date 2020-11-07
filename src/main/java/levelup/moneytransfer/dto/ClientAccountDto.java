package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class ClientAccountDto {

    private int accountId; //account
    private String accNumber; //account
    private String currencyCode; //account
    private Double balance; //balance

}

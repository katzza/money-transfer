package levelup.moneytransfer.dto;

import lombok.Data;

import java.util.Collection;
@Data
public class ClientAccountDto {

    private int accountId; //account
    private String accNumber; //account
    private String currencyCode; //account
    private String balance; //balance

    public ClientAccountDto(ClientAccountDto clientData) {
    }
}

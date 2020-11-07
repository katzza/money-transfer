package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class TransferDto {     //DTO - то, что мы считали например с GUI

    private String accNumberSender;
    private String nameReceiver;
    private String surNameReceiver;
    private String accNumberReceiver;
    private Integer transferAmount;
    private CurrencyCode currencyCode;
}

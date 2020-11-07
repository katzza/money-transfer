package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class TransferDto {     //DTO - то, что мы считали например с GUI   Data Transfer Object

    private String accNumberSender;
    private String nameReceiver;
    private String surNameReceiver;
    private String accNumberReceiver;
    private Double transferAmount;
    private CurrencyCode currencyCode;
}

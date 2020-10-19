package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class TransferDto {     //DTO - то, что мы считали например с GUI

    private String accNumberSender;
    private String nameReciever;
    private String surNameReciever;
    private String accNumberReciever;
    private Integer transferAmount;
}

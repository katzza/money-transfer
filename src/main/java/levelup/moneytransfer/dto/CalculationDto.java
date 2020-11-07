package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class CalculationDto {
     private Double transferAmountInCurrencySender;
     private Double transferAmountInCurrencyReceiver;
     private Double transferFeeInCurrencySender;
}

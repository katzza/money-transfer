package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class CalculationQueryDto {
    private String accCurrencySender;
    private String accCurrencyReceiver;
    private String transferAmountInCurrencySender;
    public CalculationQueryDto(String currencyCode, String currencyCode1, Integer transferAmount) {
    }
}

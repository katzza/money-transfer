package levelup.moneytransfer.dto;

public class CalculationQueryDto {
    private String accCurrencySender;
    private String accCurrencyReceiver;
    private String transferAmountInCurrencySender;

    public CalculationQueryDto(String currencyCode, String currencyCode1, Integer transferAmount) {
    }
}

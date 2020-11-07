package levelup.moneytransfer.dto;

import lombok.Data;

@Data
public class CalculationQueryDto {
    private String accCurrencySender;
    private String accCurrencyReceiver;
    private Double transferAmount; //toDo он же в MAR? или нет?

    public CalculationQueryDto(String accCurrencySender, String accCurrencyReceiver, Double transferAmount) {
        this.accCurrencySender = accCurrencySender;
        this.accCurrencyReceiver = accCurrencyReceiver;
        this.transferAmount = transferAmount;
    }
}

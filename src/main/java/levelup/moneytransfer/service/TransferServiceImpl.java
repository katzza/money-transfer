package levelup.moneytransfer.service;

import levelup.moneytransfer.dao.BalanceEntity;
import levelup.moneytransfer.dao.ProfitEntity;
import levelup.moneytransfer.dao.TransactionEntity;
import levelup.moneytransfer.dto.CalculationDto;
import levelup.moneytransfer.dto.CalculationQueryDto;
import levelup.moneytransfer.dto.ClientAccountDto;
import levelup.moneytransfer.dto.TransferDto;
import levelup.moneytransfer.repo.BalanceRepo;
import levelup.moneytransfer.repo.ProfitRepo;
import levelup.moneytransfer.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransferServiceImpl implements TransferService {

    private ClientAccountDto sender;
    private ClientAccountDto receiver;

    @Autowired
    private ClientDataService clientDataService;
    @Autowired
    private CalculationServiceImpl calculationService;
    @Autowired
    private BalanceRepo balanceRepo;
    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private ProfitRepo profitRepo;


    public String createTransfer(TransferDto transferDto) {
        String clientsFounded = getClients(transferDto);
        if (!clientsFounded.equals("Success")) return clientsFounded; //toDo не работало, когда были ошибки в соап-юай
        CalculationDto calculationDto = getCalculation(transferDto.getTransferAmount());
        return makeTransfer(transferDto, sender, receiver, calculationDto);
    }

    private String getClients(TransferDto transferDto) {
        sender = clientDataService.getClientData(transferDto.getAccNumberSender());
        if (sender == null) return "Sender not founded";
        receiver = clientDataService.getClientData(transferDto.getAccNumberReceiver());
        if (receiver == null) return "Receiver not founded";
        return "Success";
    }

    private CalculationDto getCalculation(Double transferAmount) {
        CalculationQueryDto calculationQueryDto =
                new CalculationQueryDto(sender.getCurrencyCode(), receiver.getCurrencyCode(), transferAmount);
        return calculationService.getCalculation(calculationQueryDto);
    }

    private String makeTransfer(TransferDto transferDto, ClientAccountDto sender, ClientAccountDto receiver, CalculationDto calculationDto) {
        if (sender.getBalance() < calculationDto.getTransferAmountInCurrencySender()) {
            return "Sender has not enough money for transfer!";
        }
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(transferDto.getTransferAmount());
        transactionEntity.setCurrency("MAR");
        transactionEntity.setTransactionTime(new Timestamp(System.currentTimeMillis()));
        transactionEntity.setAccountSender(sender.getAccountId());
        transactionEntity.setAccountReceiver(receiver.getAccountId());
        transactionRepo.save(transactionEntity);

        makeBalance(sender, transactionEntity, false, calculationDto);
        makeBalance(receiver, transactionEntity, false, calculationDto);
        makeBankProfit(transactionEntity, calculationDto);
        return "Success";
    }

    private void makeBankProfit(TransactionEntity transactionEntity, CalculationDto calculationDto) {
        ProfitEntity profitEntity = new ProfitEntity();
        profitEntity.setTransactionId(transactionEntity);
        profitEntity.setAmount(calculationDto.getTransferFeeInCurrencySender());
        profitRepo.save(profitEntity);
    }

    private void makeBalance(ClientAccountDto clientAccountDto, TransactionEntity transactionEntity,
                             boolean isReceiver, CalculationDto calculationDto) {
        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setTransactionByTransactionId(transactionEntity);
        balanceEntity.setCurrency(clientAccountDto.getCurrencyCode());
        balanceEntity.setAccountId(clientAccountDto.getAccountId());
        balanceEntity.setBalanceBefore(clientAccountDto.getBalance());
        balanceEntity.setBalanceAfter(isReceiver ? clientAccountDto.getBalance() + calculationDto.getTransferAmountInCurrencyReceiver() :
                clientAccountDto.getBalance() - (calculationDto.getTransferAmountInCurrencySender() + calculationDto.getTransferFeeInCurrencySender()));
        balanceEntity.setCurrency(clientAccountDto.getCurrencyCode());
        balanceRepo.save(balanceEntity);
    }


}


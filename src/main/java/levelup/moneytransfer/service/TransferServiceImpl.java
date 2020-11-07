package levelup.moneytransfer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import levelup.moneytransfer.dto.CalculationQueryDto;
import levelup.moneytransfer.dto.ClientAccountDto;
import levelup.moneytransfer.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private ObjectMapper objectMapper;
    private static final File transfersData = new File("C:\\Users\\emokeeva\\Documents\\Learn\\JavaCourse\\money-transfer\\src\\main\\resources\\transfers.dat");
    private ClientAccountDto sender;
    private ClientAccountDto receiver;
    private ClientDataService clientDataService;

    public String createTransfer(TransferDto transferDto) {
        String clientsFounded = getClients(transferDto);
        if (!clientsFounded.equals("Success")) return clientsFounded;
        getCalculation(transferDto.getTransferAmount());
        /*else {
            if (!isEnoughMoney(transferDto)) return "Cannot create transfer with name "
                    + transferDto.toString() + " not enough money in the account!";
            else return "";
        }*/

        return "";
    }


    private String getClients(TransferDto transferDto) {
        sender = clientDataService.getClientData(transferDto.getAccNumberSender());
        if (sender == null) return "Sender not founded";
        receiver = clientDataService.getClientData(transferDto.getAccNumberReceiver());
        if (receiver == null) return "Receiver not founded";
        return "Success";
    }

    private void getCalculation(Integer transferAmount) {
        CalculationQueryDto calculationQueryDto =
                new CalculationQueryDto(sender.getCurrencyCode(), receiver.getCurrencyCode(), transferAmount);


    }

    private boolean isEnoughMoney(TransferDto transferDto) {

        return true;
    }


}


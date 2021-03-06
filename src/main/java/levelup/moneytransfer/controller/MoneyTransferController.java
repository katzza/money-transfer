package levelup.moneytransfer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.ValidationResult;
import levelup.moneytransfer.dto.ClientAccountDto;
import levelup.moneytransfer.dto.TransferDto;
import levelup.moneytransfer.service.ClientDataService;
import levelup.moneytransfer.service.TransferService;
import levelup.moneytransfer.utils.SchemaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController  //Это удобная аннотация, которая объединяет @ Controller и @ ResponseBody -
// устраняет необходимость аннотировать каждый метод обработки запросов класса контроллера аннотацией @ ResponseBody

public class MoneyTransferController {

    @Autowired // Аннотация Autowired говорит спрингу: в это поле нужно инжектнуть бин
    private TransferService transferService;

    @Autowired
    private ClientDataService clientDataService;

    @Autowired
    private SchemaValidator schemaValidator;

    @Autowired
    private ObjectMapper objectMapper;

    // это составная аннотация, которая действует как ярлык для @RequestMapping(method = RequestMethod.POST).
    //аннотированные методы обрабатывают HTTP POST-запросы, соответствующие заданному URI expression
    @PostMapping("/transfer/create")
    public String createTransfer(@RequestBody String raw) throws JsonProcessingException {
        ValidationResult validationResult = schemaValidator.validateSchema(raw);
        Set<ValidationMessage> messages = validationResult.getValidationMessages();
        if (!messages.isEmpty()) {
            return objectMapper.writeValueAsString(messages);
        }
        TransferDto transferDto = objectMapper.readValue(raw, TransferDto.class);
        return transferService.createTransfer(transferDto);
    }

}

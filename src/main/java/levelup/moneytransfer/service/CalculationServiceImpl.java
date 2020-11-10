package levelup.moneytransfer.service;

import levelup.moneytransfer.dto.CalculationDto;
import levelup.moneytransfer.dto.CalculationQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class CalculationServiceImpl implements CalculationService {

    @Autowired
    private RestTemplate restTemplate;

    final String baseUrl = "http://localhost:7002" + "/exchangeRate/"; //toDo host 7002


    public CalculationDto getCalculation(CalculationQueryDto calculationQueryDto) {   //передаёт calculationQueryDto в ссылке

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(httpHeaders);
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder
                        .fromUriString(baseUrl)
                        .queryParam("accCurrencySender", calculationQueryDto.getAccCurrencySender())
                        .queryParam("accCurrencyReceiver", calculationQueryDto.getAccCurrencyReceiver())
                        .queryParam("transferAmount", calculationQueryDto.getTransferAmount());

        ResponseEntity<CalculationDto> calculationDto =
                restTemplate.exchange (uriComponentsBuilder.toUriString(), HttpMethod.GET, entity, CalculationDto.class, 1);
        // toDo a если не нашёл - статусы запросов
        return calculationDto.getBody();
    }

 /*   public CalculationDto getCalculation(CalculationQueryDto calculationQueryDto) {   //передаёт calculationQueryDto в ссылке

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<CalculationDto> calculationDto =
                restTemplate.exchange(baseUrl + baseUrl, HttpMethod.GET, entity, CalculationDto.class, 1);
        // toDo a если не нашёл - статусы запросов
        return calculationDto.getBody();
    }*/
}

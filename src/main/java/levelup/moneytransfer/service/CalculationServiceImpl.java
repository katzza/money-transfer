package levelup.moneytransfer.service;

import levelup.moneytransfer.dto.CalculationDto;
import levelup.moneytransfer.dto.CalculationQueryDto;
import levelup.moneytransfer.dto.ClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class CalculationServiceImpl implements CalculationService {

    @Autowired
    private RestTemplate restTemplate;

    final String baseUrl = "http://localhost:" + "/exchangeRate/";


    public CalculationDto getCalculation(CalculationQueryDto calculationQueryDto) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(httpHeaders);
        UriComponentsBuilder uriComponentsBuilder =
                UriComponentsBuilder
                        .fromUriString(baseUrl)
                        .queryParam("accCurrencySender",calculationQueryDto.getAccCurrencySender());

        ResponseEntity<CalculationDto> calculationDto =
                restTemplate.exchange(baseUrl + "UNIQUEID???", HttpMethod.GET, entity, CalculationDto.class, 1);


        // toDo a если не нашёл?
        return calculationDto.getBody();
    }
}

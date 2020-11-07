package levelup.moneytransfer.service;

import levelup.moneytransfer.dto.ClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ClientDataServiceImpl implements ClientDataService {

    @Autowired
    private RestTemplate restTemplate;

    final String baseUrl = "http://localhost:" + "/clientAccountData/";


    public ClientAccountDto getClientData(String accountId) {
        //toDo что получаем?!!
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(httpHeaders);

        ResponseEntity<ClientAccountDto> clientAccountDtoFrom =
                restTemplate.exchange(baseUrl + accountId, HttpMethod.GET, entity, ClientAccountDto.class, 1);
        System.out.println(clientAccountDtoFrom);

        // toDo a если не нашёл?
        return clientAccountDtoFrom.getBody();
    }

}

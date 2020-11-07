package levelup.moneytransfer.service;

import levelup.moneytransfer.dto.ClientAccountDto;

public interface ClientDataService {
   ClientAccountDto getClientData(String accountId);
}

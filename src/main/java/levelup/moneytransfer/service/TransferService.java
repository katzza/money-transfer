package levelup.moneytransfer.service;

import levelup.moneytransfer.dto.TransferDto;

public interface TransferService {
    String createTransfer (TransferDto transferDto);
}

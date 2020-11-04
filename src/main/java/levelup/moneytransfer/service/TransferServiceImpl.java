package levelup.moneytransfer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import levelup.moneytransfer.dto.TransferDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TransferServiceImpl implements TransferService {
    private ObjectMapper objectMapper;
    private static final File transfersData = new File("C:\\Users\\emokeeva\\Documents\\Learn\\JavaCourse\\money-transfer\\src\\main\\resources\\transfers.dat");

    public String createTransfer(TransferDto transferDto) {
        try {
          //  objectMapper.writeValue(transfersData, transferDto);
          //  File transfersData = new File("C:\\Users\\Platon\\IdeaProjects\\client-management\\data\\clients.dat");
          //  FileWriter fileWriter = new FileWriter(transfersData, Boolean.TRUE);
            String transfer = objectMapper.writeValueAsString(transferDto);
            Files.writeString(
                    Path.of("C:\\Users\\emokeeva\\Documents\\Learn\\JavaCourse\\money-transfer\\src\\main\\resources\\transfers.dat"),
                    transfer, StandardOpenOption.APPEND);
           // fileWriter.write(transfer);
            return "Success creating transfer with Id" + transferDto.toString();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return "Unable to create transfer with name" + transferDto.toString();
        }
    }
}


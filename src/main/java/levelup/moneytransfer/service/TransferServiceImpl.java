package levelup.moneytransfer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import levelup.moneytransfer.dto.TransferDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TransferServiceImpl implements TransferService {
    private ObjectMapper objectMapper;
    private static final File clientsData = new File("C:\\Users\\...");  //toDo

    public String createTransfer(TransferDto transferDto) {
        try {
            //objectMapper.writeValue(clientsData, clientDto);
            //File clientsData = new File("C:\\Users\\Platon\\IdeaProjects\\client-management\\data\\clients.dat");
            //FileWriter fileWriter = new FileWriter(clientsData, Boolean.TRUE);
            String client = objectMapper.writeValueAsString(transferDto);
            Files.writeString(Path.of("C:\\Users\\clients.dat"), client, StandardOpenOption.APPEND); //toDo
            //fileWriter.write(client);
            return "Success creating transfer with Id" + transferDto.toString();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return "Unable to create transfer with name" + transferDto.toString();
        }
    }
}


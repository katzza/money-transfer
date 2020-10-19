package levelup.moneytransfer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service // бин Spring, @Service — фасад для некоторой бизнес логики
public class SchemaValidator {

    @Autowired
    private ObjectMapper objectMapper;

    private final static JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
    public ValidationResult validateSchema(String jsonInstance) throws JsonProcessingException {

        String rawSchema = null;
        try {
            rawSchema = new String(Files.readAllBytes
                    (Paths.get("C:\\Users\\emokeeva\\Documents\\Learn\\JavaCourse\\......")));  //todo
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());

        }

        JsonSchema finalSchema = factory.getSchema(rawSchema);
        JsonNode rawNode = objectMapper.readTree(jsonInstance);

        return finalSchema.validateAndCollect(rawNode);

    }
}


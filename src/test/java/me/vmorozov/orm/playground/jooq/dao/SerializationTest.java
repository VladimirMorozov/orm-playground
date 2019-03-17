package me.vmorozov.orm.playground.jooq.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.vmorozov.orm.playground.jooq.generated.tables.records.EmployeeRecord;
import org.junit.Test;

public class SerializationTest {

    //@Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void trySerialize() throws JsonProcessingException {
        EmployeeRecord record = new EmployeeRecord(1, 2, "hello", "bye");
        System.out.println(objectMapper.writeValueAsString(record));
    }

}

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTest {

    @Test
    void name() throws JsonProcessingException {
        Time expectedTime = new Time();
        expectedTime.setZonedDateTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(1548236954181L), ZoneOffset.UTC));
        expectedTime.setLocalDate(LocalDate.ofInstant(Instant.ofEpochMilli(1532908800000L), ZoneOffset.UTC));
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                .disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);

        String timeJson = "{\n" +
                "\"zonedDateTime\": 1548236954181,\n" +
                "\"localDate\": 1532908800000, \n " +
                "}";

        Time actualTime = objectMapper.readValue(timeJson, Time.class);
        assertEquals(expectedTime, actualTime);
    }
}

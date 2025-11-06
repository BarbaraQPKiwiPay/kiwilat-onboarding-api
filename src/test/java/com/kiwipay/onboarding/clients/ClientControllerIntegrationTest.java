package com.kiwipay.onboarding.clients;

import com.kiwipay.onboarding.titular.domain.model.entities.Address;
import com.kiwipay.onboarding.titular.domain.model.valueobjects.DocumentType;
import com.kiwipay.onboarding.titular.domain.model.valueobjects.Gender;
import com.kiwipay.onboarding.titular.domain.model.valueobjects.MaritalStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createClient_shouldReturnCreated() throws Exception {
        ClientCreateRequestDto dto = new ClientCreateRequestDto();
        dto.setDocumentType(DocumentType.DNI);
        dto.setDocumentNumber("12345678");
        dto.setFirstNames("Juan");
        dto.setLastNames("Perez");
        dto.setMaritalStatus(MaritalStatus.SINGLE);
        dto.setGender(Gender.M);
        dto.setBirthDate(LocalDate.of(1990, 1, 1));
        dto.setEmail("juan.perez@example.com");
        dto.setPhone("+51987654321");
        dto.setAddress(new Address("1501", "150101", "15010101", "Av. Principal 123"));
        mockMvc.perform(post("/api/v1/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}

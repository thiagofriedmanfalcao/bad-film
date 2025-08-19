package com.badfilm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class BadFilmControllerIntegrationTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenIntervalWinners_whenGetFilms_thenStatus200() throws Exception {
        MvcResult result = mvc.perform(get("/bad-films/interval-winners")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String responseAsString = result.getResponse().getContentAsString();
        JsonNode jsonResponse = objectMapper.readTree(responseAsString);

        int intervalMin = Integer.parseInt(jsonResponse.get("min").get(0).get("interval").asText());
        int intervalMax = Integer.parseInt(jsonResponse.get("max").get(0).get("interval").asText());

        Assertions.assertEquals(1, intervalMin);
        Assertions.assertEquals(13, intervalMax);
    }
}

package com.traskindustries.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.traskindustries.messages.*;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiContainerTests {

    private final static String BASE_PATH = "/mutant/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void testVerifyIsMutantReturnsHttpOK()
        throws Exception {
        final String[] dna = {
        //       012345
                "CTCTCT",
                "AGAGAG",
                "AATTTT",
                "AAAAAA",
                "TTTTAA",
                "AAAAAA"};
        final VerifyIsMutantRequest request  =
                new VerifyIsMutantRequest(dna);
        final String requestString =
                mapper
                .writeValueAsString(request);

        mockMvc
        .perform(
            post(BASE_PATH)
            .content(requestString)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    }
}

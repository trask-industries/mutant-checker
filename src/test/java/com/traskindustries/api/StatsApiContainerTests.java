package com.traskindustries.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.traskindustries.messages.GetDNAStatsResponse;
import com.traskindustries.model.CheckedDNA;
import com.traskindustries.repository.CheckedDNARepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsApiContainerTests {

    private final static String BASE_PATH = "/stats/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    CheckedDNARepository repository;

    @Test
    public void testVerifyGetStatsReturnsHttpOK()
            throws Exception {

        repository.deleteAll();
        repository.save(new CheckedDNA.Builder().dna("AA|TT").result(true).build());
        repository.save(new CheckedDNA.Builder().dna("TA|TT").result(false).build());
        repository.save(new CheckedDNA.Builder().dna("TA|TA").result(false).build());

        final MvcResult result =
            mockMvc
            .perform(
                    get(BASE_PATH))
            .andExpect(
                status()
                .isOk())
            .andReturn();

        GetDNAStatsResponse stats =
                mapper
                .readValue(
                    result
                    .getResponse()
                    .getContentAsString(),
                    GetDNAStatsResponse.class);
        assertThat(stats).isNotNull();
        assertThat(stats.getHumans()).isEqualTo(2);
        assertThat(stats.getMutants()).isEqualTo(1);
        assertThat(stats.getRatio()).isBetween(0.0,1.0);
    }
}

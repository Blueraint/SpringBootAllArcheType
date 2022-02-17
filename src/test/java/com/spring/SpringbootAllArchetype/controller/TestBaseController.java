package com.spring.SpringbootAllArchetype.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Junit Api Unit Test Case
 */

@ActiveProfiles({"dev","database"})
@SpringBootTest
@AutoConfigureMockMvc // webMvc Test configuration annotation
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBaseController {

    /* webMvc Test configuration bean injection */
    @Autowired
    MockMvc mockMvc;

    @Test
    @Order(1)
    public void getIndex() throws Exception {
        String data = "GET";

        // ModelMap
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data",data);

        /* Mockmvc 로 url pattern 에 대한 예측값을 test */
        MvcResult result = mockMvc.perform(get("/") // method
                .params(params)) // queryString param
                .andDo(print()) // response 이후 print 수행
                .andExpect(status().is(HttpStatus.OK.value())) // Status 예측
                .andReturn(); // response 반환

        String content = result.getResponse().getContentAsString();

        Assertions.assertThat(content.contains(data));
    }

    @Test
    @Order(2)
    public void postIndex() throws Exception {
        String data = "POST";

        // ModelMap
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("data",data);

        /* Mockmvc 로 url pattern 에 대한 예측값을 test */
        MvcResult result = mockMvc.perform(post("/").params(params))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        Assertions.assertThat(content.contains(data));
    }
}

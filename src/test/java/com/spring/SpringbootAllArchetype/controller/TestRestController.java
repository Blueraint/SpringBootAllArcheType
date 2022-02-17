package com.spring.SpringbootAllArchetype.controller;

import com.google.gson.Gson;
import com.spring.SpringbootAllArchetype.controller.dto.ResponseDeptDto;
import com.spring.SpringbootAllArchetype.core.dto.MessageDto;
import org.assertj.core.api.Assertions;
import com.google.gson.reflect.TypeToken;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"dev","database"})
@SpringBootTest
@AutoConfigureMockMvc // webMvc Test configuration annotation
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRestController {

    @Autowired
    MockMvc mockMvc;

    private String version = "v1";

    @Test
    @Order(1)
    public void api_insert() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("deptNo","10");
        params.add("deptName","accounting 회계");
        params.add("location","newyork똥");

        /* Api result
        * insert : POST method */
        MvcResult result = mockMvc.perform(post("/api/" + version + "/dept").params(params))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        /* Gson library 를 이용한 JSON Parse */
        Gson gson = new Gson();
        ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class); // json parser
        Assertions.assertThat(responseDeptDto.getDeptNo()).isEqualTo(10);
    }

    @Test
    @Order(2)
    public void api_update() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("deptNo","10");
        params.add("deptName","Music");
        params.add("location","New seoul");

        /* Api result
        * update : PUT method */
        MvcResult result = mockMvc.perform(put("/api/" + version + "/dept").params(params))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        /* Gson library 를 이용한 JSON Parse */
        Gson gson = new Gson();
        ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class); // json parser
        Assertions.assertThat(responseDeptDto.getDeptName()).isEqualTo("Music");
    }

    @Test
    @Order(3)
    public void api_select() throws Exception {
        /* Api result
        * select : GET method */
        MvcResult result = mockMvc.perform(get("/api/" + version + "/dept"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        /* Gson library 를 이용한 JSON Parse */
        Gson gson = new Gson();
        List<ResponseDeptDto> responseDeptDtoList = gson.fromJson(content, new TypeToken<List<ResponseDeptDto>>(){}.getType()); // json parser
        responseDeptDtoList.forEach(responseDeptDto -> System.out.println("&&&&&&&&&&&" + responseDeptDto.toString()));
    }

    @Test
    @Order(4)
    public void api_selectDetail() throws Exception {
        /* Api result
         * select : GET method */
        MvcResult result = mockMvc.perform(get("/api/" + version + "/dept/10"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        /* Gson library 를 이용한 JSON Parse */
        Gson gson = new Gson();
        ResponseDeptDto responseDeptDto = gson.fromJson(content, ResponseDeptDto.class); // json parser
        Assertions.assertThat(responseDeptDto.getDeptNo()).isEqualTo(10);
    }

    @Test
    @Order(5)
    public void api_delete() throws Exception {
        /* Api result
         * select : DELETE method */
        MvcResult result = mockMvc.perform(delete("/api/" + version + "/dept/10"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        /* Gson library 를 이용한 JSON Parse */
        Gson gson = new Gson();
        MessageDto messageDto = gson.fromJson(content, MessageDto.class); // json parser
        Assertions.assertThat(messageDto.getMessage()).isEqualTo("DELETE");
    }
}

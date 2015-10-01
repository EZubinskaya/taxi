package com.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kzub on 10/1/2015.
 */
public class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();

    }
    @Test
    public void testWelcomePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }
}
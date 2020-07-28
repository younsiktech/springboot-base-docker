package kr.com.younsiktech.springboot.base.web.controller;

import org.hamcrest.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @RunWith(SpringRunner.class) // junit4
@ExtendWith(SpringExtension.class) // junit5
@WebMvcTest(controllers = WebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void returnHomeTest() throws Exception {
        String home = "home";

        mvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string(home));
    }

    @Test
    public void returnDtoTest() throws Exception {
        String name = "home";
        int amount = 100;

        mvc.perform(get("/home/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(name)))
                .andExpect(jsonPath("$.amount", Matchers.is(amount)));
    }

}
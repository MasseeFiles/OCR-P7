package com.nnk.springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
public class LoginControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
    private UserService userService;

    @MockBean
    private RuleService ruleNameService;

    @Test
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturn_ObjectViewAndModel() throws Exception {
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders
                        .get("login"))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());

        // Extract ModelAndView object from the result
        ModelAndView modelAndView = resultActions.andReturn().getModelAndView();

        // Assertions on ModelAndView object
        assertNotNull(modelAndView);
        assertEquals("/login", modelAndView.getViewName());

//        MvcResult result =
//                mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .get("/login")
//                )
//                .andReturn();
//
//        ObjectMapper mapper = new ObjectMapper();
//        ModelAndView mavTest = result.getModelAndView();
//        assertNotNull(mavTest);
//        assert mavTest != null;
//        assertEquals(Objects.requireNonNull(mavTest.getView()).toString(), "login");



    }
}

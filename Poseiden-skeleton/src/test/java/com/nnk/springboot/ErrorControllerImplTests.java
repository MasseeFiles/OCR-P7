package com.nnk.springboot;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest
public class ErrorControllerImplTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private RatingService ratingService;
    @MockBean
    private BidListService bidListService;
    @MockBean
    private CurvePointService curvePointService;
    @MockBean
    private TradeService tradeService;
    @MockBean
    private RuleService ruleNameService;

    @Test
    @WithMockUser(username = "userEmail1")
    void error_shouldReturnObjectModelAndView() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/error"))
                        .andReturn();

        ModelAndView modelAndViewTest = mvcResult.getModelAndView();
        assertThat(modelAndViewTest).isNotNull();
        assertThat("/403").isEqualTo(modelAndViewTest.getViewName());
    }
}

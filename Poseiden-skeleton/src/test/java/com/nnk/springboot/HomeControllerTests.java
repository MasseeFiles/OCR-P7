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

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
public class HomeControllerTests {
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
    @WithMockUser(username = "userEmail1")
    void home_shouldReturnObjectModelAndViewView() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/home"))
                        .andReturn();

        ModelAndView modelAndViewTest = mvcResult.getModelAndView();
        String viewExpected = modelAndViewTest.getViewName();
        assertThat(viewExpected).isEqualTo("/home");
    }
}

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;

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
    @WithMockUser(username = "user1")  // a ajouter apres config de spring security
    void login_shouldReturn_Object_ModelAndView() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/login"))
                        .andExpect(MockMvcResultMatchers
                                .status().isOk())
                        .andExpect(MockMvcResultMatchers
                                .model().attributeExists("mav"))
                        .andReturn();
//
//        ModelAndView modelAndViewTest = mvcResult.getModelAndView();
//
//        assertThat(modelAndViewTest).isNotNull();
//        assertThat("/login").isEqualTo(modelAndViewTest.getViewName());
    }
//
//    @Test
////    @WithMockUser(username = "user")  // a ajouter apres config de spring security
//    void getAllUserArticles_shouldReturn_Object_ModelAndView() throws Exception {
//        MvcResult mvcResult =
//                mockMvc.perform(MockMvcRequestBuilders
//                                .get("secure/article-details"))
//                        .andExpect(MockMvcResultMatchers
//                                .status().isOk())
//                        .andReturn();
//
//        ModelAndView modelAndView = mvcResult.getModelAndView();
//
//        assertThat(modelAndView).isNotNull();
//        assertThat(modelAndView.getModel().containsValue("users"));
//        assertThat("user/list").isEqualTo(modelAndView.getViewName());
//    }
//
//    @Test
////    @WithMockUser(username = "user")  // a ajouter apres config de spring security
//    void error_shouldReturn_ObjectViewAndModel() throws Exception {
//        MvcResult mvcResult =
//                mockMvc.perform(MockMvcRequestBuilders
//                                .get("error"))
//                        .andExpect(MockMvcResultMatchers
//                                .status().isOk())
//                        .andReturn();
//
//        ModelAndView modelAndView = mvcResult.getModelAndView();
//
//        assertThat(modelAndView).isNotNull();
//        assertThat("403").isEqualTo(modelAndView.getViewName());
//    }
}

package com.nnk.springboot;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("home"));
    }

    @Test
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void adminHome_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/admin/home")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list"));
    }
}

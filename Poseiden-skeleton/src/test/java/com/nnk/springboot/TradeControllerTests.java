package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest
public class TradeControllerTests {
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
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trade/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("trades"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void addTradeForm_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trade/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/validate")
                        .param("tradeId", "1")
                        .param("account", "1")
                        .param("type", "1")
                        .param("buyQuantity", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/trade/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/validate")
                        .param("id", "100")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void showUpdateForm_shouldReturnView() throws Exception {
        //GIVEN
        Trade tradeTest = new Trade();
        when(tradeService.findById(1)).thenReturn(tradeTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trade/update/1")
                )

                //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("trade"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateTrade_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/update/1")
                        .param("tradeId", "1")
                        .param("account", "1")
                        .param("type", "1")
                        .param("buyQuantity", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/trade/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateTrade_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/update/1")
                        .param("id", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/update"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteRating_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trade/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/trade/list")
                );
    }
}




package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
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
class BidListControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private RatingService ratingService;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private UserService userService;

    @MockBean
    private RuleService ruleNameService;

    @Test
    @WithMockUser(username = "userEmail1")
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bidList/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("bidList/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("bidLists"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void addBidForm_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bidList/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("bidList/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bidList/validate")
                        .param("account", "account1")
                        .param("type", "type2")
                        .param("bidQuantity", "1.0")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bidList/validate")
                        .param("id", "100")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("bidList/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void showUpdateForm_shouldReturnView() throws Exception {
        //GIVEN
        BidList bidListTest = new BidList();
        when(bidListService.findById(1)).thenReturn(bidListTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bidList/update/1")
                )
        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("bidList/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("bidList"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateBidList_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bidList/update/1")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("bidQuantity", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateBidList_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bidList/update/1")
                        .param("id", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("bidList/update"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteBidList_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bidList/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list")
                );
    }
}
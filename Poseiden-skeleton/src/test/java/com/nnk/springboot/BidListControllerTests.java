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

@WebMvcTest
class BidListControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BidListService bidListService;

    @MockBean   //mock necessaire pour creer le contexte du test (pas autowire)
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
        // a ajouter apres config de spring security
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
    void addBidForm_shouldReturnView() throws Exception {
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
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/bidList/validate")
//                        .param("id", "1")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("bidQuantity", "1.0")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list"));
    }

    //Exemple de test sur renvoi d'exception
//    @Test
//    @WithMockUser(username = "userEmail1")
//    void validate_shouldThrowIllegalArgumentException() throws Exception {
//        assertThatThrownBy(
//                () -> mockMvc.perform(MockMvcRequestBuilders
//                        .post("/bidList/validate")
//                        .param("id", "100")
//                ))
//                .hasCauseInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("BidList provided is not valid - Id used : 100");
//    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnClientError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/bidList/validate")
                        .param("id", "100")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());  //a cause de         return "redirect:/bidList/list"; ???
//            .andExpect(MockMvcResultMatchers
//                    .view().name("bidList/add"));
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
    void updateBidList_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bidList/update/1")
                        .param("account", "account1")
                        .param("type", "type1")
                        .param("bidQuantity", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/bidList/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateBidList_shouldReturnClientError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/bidList/update/1")
                        .param("id", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is4xxClientError());      //clientError en fonction de uri de fin du controller : "redirect:/bidList/list" ???
//                .andExpect(MockMvcResultMatchers
//                .view().name("/bidList/update"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteBid_shouldReturnView_Redirect() throws Exception {
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
package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

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
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
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
//    @WithMockUser - a ajouter apres configuration spring security
    void addTradeForm_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trade/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("trade/add"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/trade/validate")
                        .param("tradeId", "1")
                        .param("account", "1")
                        .param("type", "1")
                        .param("buyQuantity", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/trade/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/validate")
                        .param("tradeId", "100")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Trade provided is not valid - Id used : 100");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
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
//    @WithMockUser - a ajouter apres configuration spring security
    void updateTrade_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/update/1")
                        .param("tradeId", "1")
                        .param("account", "1")
                        .param("type", "1")
                        .param("buyQuantity", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/trade/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void updateTrade_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/trade/update/1")
                        .param("tradeId", "1")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Trade provided is not valid - Id used : 1");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void deleteRating_shouldReturnView_Redirect() throws Exception {
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




package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
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
public class CurvePointControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;
    @MockBean   //mock necessaire pour creer le contexte du test (pas autowire)
    private RatingService ratingService;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private UserService userService;

    @MockBean
    private RuleService ruleNameService;

    @Test
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/curvePoint/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("curvePoint/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("curvePoints"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void addRatingForm_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/curvePoint/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("curvePoint/add"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/validate")
                        .param("id", "1")
                        .param("term", "1.0")
                        .param("value", "1.0")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/curvePoint/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/validate")
                        .param("id", "100")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CurvePoint provided is not valid - Id used : 100");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void showUpdateForm_shouldReturnView() throws Exception {
        //GIVEN
        CurvePoint curvePointTest = new CurvePoint();
        when(curvePointService.findById(1)).thenReturn(curvePointTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/curvePoint/update/1")
                )

        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("curvePoint/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("curvePoint"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void updateCurvePoint_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/update/1")
                        .param("term", "1.0")
                        .param("value", "1.0")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/curvePoint/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void updateCurvePoint_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/update/1")
                        .param("id", "1") //exception car certains attributs à valider sont absents
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CurvePoint provided is not valid - Id used : 1");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void deleteCurvePoint_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/curvePoint/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/curvePoint/list")
                );
    }
}





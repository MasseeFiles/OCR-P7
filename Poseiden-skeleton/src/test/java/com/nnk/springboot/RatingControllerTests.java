package com.nnk.springboot;

import com.fasterxml.jackson.core.type.TypeReference;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest
public class RatingControllerTests {
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private DBDataInitializerTest dBDataInitializerTest;
    @MockBean   //mock necessaire pour creer le contexte du test (pas autowire)
    private RatingService ratingService;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RuleService ruleNameService;

    @Test
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                                .get("/rating/list")
                        )
                        .andExpect(MockMvcResultMatchers
                                .status().isOk())
                        .andExpect(MockMvcResultMatchers
                                .view().name("rating/list"))
                        .andExpect(MockMvcResultMatchers
                                .model().attributeExists("ratings"))
                        .andReturn();

        ObjectMapper mapper = new ObjectMapper();

        List<Rating> listActual = (List<Rating>)result.getModelAndView().getModel().get("ratings");
//        System.out.println(result.getModelAndView().getModel().get("ratings"));
        assertEquals(listActual.size(),3);

//                .andExpect(MockMvcResultMatchers
//                        .model().getAttribute

        //TODO : verifier type (list)     /// alternative : essayer ace and return
//                .andExpect(MockMvcResultMatchers
//                        .model().attribute("ratings", null).isInstanceOf(List.class);

    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void addRatingForm_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rating/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("rating/add"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/rating/validate")
                        .param("ratingId", "1")
                        .param("moodysRating", "1")
                        .param("sandPRating", "1")
                        .param("fitchRating", "1")
                        .param("orderNumber", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/rating/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/rating/validate")
                        .param("ratingId", "100")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Rating provided is not valid - Id used : 100");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void showUpdateForm_shouldReturnView() throws Exception {
        //GIVEN
        Rating ratingTest = new Rating();
        when(ratingService.findById(1)).thenReturn(ratingTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rating/update/1")
                )

        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("rating/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("rating"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void updateRating_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/rating/update/1")
                        .param("moodysRating", "1")
                        .param("sandPRating", "1")
                        .param("fitchRating", "1")
                        .param("orderNumber", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/rating/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void updateRating_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/rating/update/1")
                        .param("ratingId", "1") //exception car certains attributs à valider sont absents
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Rating provided is not valid - Id used : 1");
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void deleteRating_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/rating/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/rating/list")
                );
    }
}




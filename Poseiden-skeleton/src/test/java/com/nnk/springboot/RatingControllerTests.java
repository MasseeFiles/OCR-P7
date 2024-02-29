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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
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
    @WithMockUser(username = "userEmail1")
    void home_shouldReturnViewAndModelUpdated() throws Exception {
//        //GIVEN
//        List<Rating> ratingsTest = new ArrayList<>();
//
//        Rating rating1 = new Rating(1, "AA", "BB", "CC", 1);
//        Rating rating2 = new Rating(2, "AA", "BB", "CC", 1);
//        ratingsTest.add(rating1);
//        ratingsTest.add(rating2);
//
//        when(ratingService.findAll()).thenReturn(ratingsTest);

        //WHEN
        MvcResult result =
                mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                                .get("/rating/list")
                        )
        //THEN
                        .andExpect(MockMvcResultMatchers
                                .status().isOk())
                        .andExpect(MockMvcResultMatchers
                                .view().name("rating/list"))
                        .andExpect(MockMvcResultMatchers
                                .model().attributeExists("ratings"))
                        .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        List<Rating> listActual = (List<Rating>)result.getModelAndView().getModel().get("ratings");
        assertEquals(listActual.size(),2);
    }

    @Test
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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




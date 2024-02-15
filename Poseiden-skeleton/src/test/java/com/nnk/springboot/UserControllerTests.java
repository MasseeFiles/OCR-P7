package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
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
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private RatingService ratingService;

    @MockBean
    private BidListService bidListService;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
    private TradeService tradeService;

    @MockBean
    private RuleService ruleNameService;

    @Test
//    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/user/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("users"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void addUserForm_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/add"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/user/validate")
                        .param("userId", "1")
                        .param("userName", "1")
                        .param("password", "1")
                        .param("fullName", "1")
                        .param("role", "admin")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void validate_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/validate")
                        .param("userId", "100")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User provided is not valid - Id used : 100");
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
    void updateUser_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/update/1")
                        .param("userName", "1")
                        .param("password", "1")
                        .param("fullName", "1")
                        .param("role", "admin")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/list"));
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




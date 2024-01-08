package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest
public class RatingControllerTest {     //Test unitaire sur un controller
    @MockBean
    private RatingService ratingService;
    @Autowired
    private MockMvc mockMvc;

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        List<Rating> ratingsTest = new ArrayList<Rating>();
        Rating ratingTestToAdd = new Rating(1, "AA", "AA" , "AA" , 1);
        ratingsTest.add(ratingTestToAdd);

        when(ratingService.findAll()).thenReturn(ratingsTest);

        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/rating/list"))
                .andExpect(MockMvcResultMatchers
                        .view().name("rating/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("ratings"));
    }
//
//    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void addRatingForm_shouldReturnView() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .get("/rating/add")
//                        .param("ratingId", "1")
//                        .param("moodysRating", "moodys1")
//                        .param("sandPRating", "sandp1")
//                        .param("fitchRating", "fitch1")
//                        .param("orderNumber", "1")
//                )
//                .andExpect(MockMvcResultMatchers
//                        .status().isOk())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("rating/add"));
//    }
//
//    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void validate_shouldReturnViewRedirect() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                .post("/rating/validate")
//                .param("ratingId", "1"))
//                .andExpect(MockMvcResultMatchers
//                        .status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/rating/list"));
//    }
//
//    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void validate_shouldThrowRuntimeException() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .post("/rating/validate")
//                        .param("ratingId", "1000"))
//                .andExpect((result) -> {
//                    Throwable throwable = result.getResolvedException();
//
//                    assertThat(throwable).isInstanceOf(RuntimeException.class);
//                    assertThat(throwable.getMessage()).isEqualTo("Rating provided is not valid");
//                });
//    }
//
//        @Test
////    @WithMockUser - a ajouter apres configuration spring security
//        void showUpdateForm_shouldReturnView() throws Exception {
//            mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                            .get("/rating/update/1"))
//                    .andExpect(MockMvcResultMatchers
//                            .status().isOk())
//                    .andExpect(MockMvcResultMatchers
//                            .view().name("rating/update"));
//        }
//    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void updateRating_shouldReturnView_Redirect() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .post("/rating/update")
//                        .param("ratingId", "1"))
//                .andExpect(MockMvcResultMatchers
//                        .status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/rating/list"));
//    }
//
//    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void updateRating_shouldThrowRuntimeException() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .post("/rating/update")
//                        .param("ratingId", "1000"))
//                .andExpect((result) -> {
//                    Throwable throwable = result.getResolvedException();
//
//                    assertThat(throwable).isInstanceOf(RuntimeException.class);
//                    assertThat(throwable.getMessage()).isEqualTo("Rating provided is not valid");
//                });
//    }



    //    @Test
////    @WithMockUser - a ajouter apres configuration spring security
//    void deleteRating_shouldReturnView_Redirect() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
//                        .get("/rating/delete")
//                        .param("id", "1000"))
    //                .andExpect(MockMvcResultMatchers
//                        .status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("redirect:/rating/list"));
//                });
//    }





}














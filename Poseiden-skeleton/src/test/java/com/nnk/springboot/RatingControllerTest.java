package com.nnk.springboot;

import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
//@SpringBootTest
//@AutoConfigureMockMvc
public class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private RatingService ratingService;

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldReturnView_Rating_ListAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/rating/list"))
                .andExpect(MockMvcResultMatchers
                        .view().name("rating/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("ratings"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldReturnView_Rating_Add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/rating/add")
                        .param("ratingId", "1")
                        .param("moodysRating", "moodys1")
                        .param("sandPRating", "sandp1")
                        .param("fitchRating", "fitch1")
                        .param("orderNumber", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("rating/add"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldReturnView_Redirect_Rating_List_Validate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                .post("/rating/validate")
                .param("ratingId", "1"))
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/rating/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldThrowRuntimeException_Validate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/rating/validate")
                        .param("ratingId", "1000"))
                .andExpect((result) -> {
                    Throwable throwable = result.getResolvedException();

                    assertThat(throwable).isInstanceOf(RuntimeException.class);
                    assertThat(throwable.getMessage()).isEqualTo("Rating provided is not valid");
                });
    }

        @Test
//    @WithMockUser - a ajouter apres configuration spring security
        void shouldReturnView_Rating_Update() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                            .get("/rating/update/1"))
                    .andExpect(MockMvcResultMatchers
                            .status().isOk())
                    .andExpect(MockMvcResultMatchers
                            .view().name("rating/update"));
        }
    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldReturnView_Redirect_Rating_List_Update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/rating/update")
                        .param("ratingId", "1"))
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/rating/list"));
    }

    @Test
//    @WithMockUser - a ajouter apres configuration spring security
    void shouldThrowRuntimeException_Update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/rating/update")
                        .param("ratingId", "1000"))
                .andExpect((result) -> {
                    Throwable throwable = result.getResolvedException();

                    assertThat(throwable).isInstanceOf(RuntimeException.class);
                    assertThat(throwable.getMessage()).isEqualTo("Rating provided is not valid");
                });
    }
}














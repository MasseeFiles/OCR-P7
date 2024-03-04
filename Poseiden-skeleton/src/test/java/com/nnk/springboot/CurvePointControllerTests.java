package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest
public class CurvePointControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CurvePointService curvePointService;

    @MockBean
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
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
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/validate")
                        .param("id", "1")
                        .param("term", "1.0")
                        .param("value", "1.0")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/curvePoint/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
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
    @WithMockUser(username = "userEmail1")
    void updateCurvePoint_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/curvePoint/update/1")
                        .param("term", "1.0")
                        .param("value", "1.0")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/curvePoint/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateCurvePoint_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/curvePoint/update/1")
                        .param("id", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("curvePoint/update"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteCurvePoint_shouldReturnRedirectView() throws Exception {
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





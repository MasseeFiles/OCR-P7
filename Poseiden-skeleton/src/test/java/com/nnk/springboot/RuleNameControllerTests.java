package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
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

@WebMvcTest
public class RuleNameControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RuleService ruleNameService;
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

    @Test
    @WithMockUser(username = "userEmail1")
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .get("/ruleName/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("ruleName/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("ruleNames"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void addRuleForm_shouldReturnView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ruleName/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("ruleName/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnViewRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders    //methode perform sert à envoyer la request lors du test
                        .post("/ruleName/validate")
                        .param("ruleId", "1")
                        .param("name", "1")
                        .param("description", "1")
                        .param("json", "1")
                        .param("template", "1")
                        .param("sqlStr", "1")
                        .param("sqlPart", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/ruleName/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/validate")
                        .param("ruleId", "100")
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("RuleName provided is not valid - Id used : 100");
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void showRuleNameForm_shouldReturnView() throws Exception {
        //GIVEN
        RuleName ruleNameTest = new RuleName();
        when(ruleNameService.findById(1)).thenReturn(ruleNameTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ruleName/update/1")
                )

        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("ruleName/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("ruleName"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateRuleName_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/update/1")
                        .param("ruleId", "1")
                        .param("name", "1")
                        .param("description", "1")
                        .param("json", "1")
                        .param("template", "1")
                        .param("sqlStr", "1")
                        .param("sqlPart", "1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/ruleName/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateruleName_shouldThrowIllegalArgumentException() throws Exception {
        assertThatThrownBy(
                () -> mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/update/1")
                        .param("ruleId", "1") //exception car certains attributs à valider sont absents
                ))
                .hasCauseInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("RuleName provided is not valid - Id used : 1");
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteRuleNameRating_shouldReturnView_Redirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/ruleName/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/ruleName/list")
                );
    }
}

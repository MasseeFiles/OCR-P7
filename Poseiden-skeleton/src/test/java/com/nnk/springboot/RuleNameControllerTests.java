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

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest
public class RuleNameControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RuleService ruleNameService;
    @MockBean
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
        mockMvc.perform(MockMvcRequestBuilders
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
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/validate")
                        .param("ruleId", "1")
                        .param("name", "1")
                        .param("description", "1")
                        .param("json", "1")
                        .param("template", "1")
                        .param("sqlStr", "1")
                        .param("sqlPart", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/ruleName/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/validate")
                        .param("id", "100")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("ruleName/add"));
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
    void updateRuleName_shouldRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/update/1")
                        .param("ruleId", "1")
                        .param("name", "1")
                        .param("description", "1")
                        .param("json", "1")
                        .param("template", "1")
                        .param("sqlStr", "1")
                        .param("sqlPart", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/ruleName/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateRuleName_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/ruleName/update/1")
                        .param("id", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("ruleName/update"));
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

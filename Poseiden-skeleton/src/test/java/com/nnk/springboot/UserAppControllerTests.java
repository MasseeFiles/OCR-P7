package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.UserApp;
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
public class UserAppControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

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
    @WithMockUser(username = "userEmail1")
    void home_shouldReturnViewAndModelUpdated() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/list")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/list"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("userApps"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void addUserForm_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/add")
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/add"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/validate")
//                        .param("userId", "1")
                        .param("userName", "userName1")
                        .param("password", "Pass123!")
                        .param("fullName", "userFullName1")
                        .param("role", "admin")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void validate_shouldReturnFormView() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders
                            .post("/user/validate")
                            .param("id", "100")
                            .with(csrf())
                    )
                    .andExpect(MockMvcResultMatchers
                            .status().isOk())
                    .andExpect(MockMvcResultMatchers
                            .view().name("user/add"));
        }

    @Test
    @WithMockUser(username = "userEmail1")
    void showUpdateForm_shouldReturnView() throws Exception {
        //GIVEN
        UserApp userAppTest = new UserApp();
        when(userService.findById(1)).thenReturn(userAppTest);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/update/1")
                )

        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/update"))
                .andExpect(MockMvcResultMatchers
                        .model().attributeExists("userApp"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateUser_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/update/1")
                        .param("userName", "1")
                        .param("password", "Pass123!")
                        .param("fullName", "1")
                        .param("role", "admin")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/list"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void updateUser_shouldReturnFormView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/update/1")
                        .param("id", "1")
                        .with(csrf())
                )
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(MockMvcResultMatchers
                        .view().name("user/update"));
    }

    @Test
    @WithMockUser(username = "userEmail1")
    void deleteUserApp_shouldReturnRedirectView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/delete/1")
                )
                .andExpect(MockMvcResultMatchers
                        .status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers
                        .view().name("redirect:/user/list")
                );
    }
}




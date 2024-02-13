//package com.nnk.springboot;
//
//import com.nnk.springboot.services.BidListService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@WebMvcTest
//class BidListControllerTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BidListService bidListService;
//
//    @Test
////    @WithMockUser(username = "userEmail1")  // a ajouter apres config de spring security
//    void home_shouldReturnViewAndModelUpdated() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/bidList/list")
//                )
//                .andExpect(MockMvcResultMatchers
//                        .status().isOk())
//                .andExpect(MockMvcResultMatchers
//                        .view().name("bidList/list"))
//                .andExpect(MockMvcResultMatchers
//                        .model().attributeExists("bidLists"));
//    }
////
////    @Test
////    void addBidForm() {
////    }
////
////    @Test
////    void validate() {
////    }
////
////    @Test
////    void showUpdateForm() {
////    }
////
////    @Test
////    void updateBid() {
////    }
////
////    @Test
////    void deleteBid() {
////    }
//}
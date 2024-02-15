package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BidListServiceTests {
    @Autowired
    private BidListService bidListService;
    @Mock
    private BidListRepository bidListRepository;

    @BeforeEach
    public void setup() {
        bidListRepository = mock(BidListRepository.class);
    }

    @Test
    void findById_Ok() {
        //GIVEN
        BidList bidListExpected = new BidList();
        bidListExpected.setId(1);
        bidListExpected.setAccount("account1");
        bidListExpected.setType("type1");
        bidListExpected.setBidQuantity(1.0);

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidListExpected));

        //WHEN
        BidList bidListActual = bidListService.findById(1);

        //THEN
        assertThat(bidListExpected).usingRecursiveComparison()
                .isEqualTo(bidListActual);
    }

    @Test
    void findById_BidList_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //id = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            bidListService.findById(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("BidList not found : Id used 10");
    }

    @Test
    void update_BidList_Not_Found() {
        //GIVEN
        BidList bidListNotInDB = new BidList();
        bidListNotInDB.setId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            bidListService.update(bidListNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("BidList to update not found : Id used 100");
    }

    @Test
    void delete_BidList_Not_Found() {
        //GIVEN
        Integer idNotInDb = 100;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            bidListService.delete(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("BidList to delete not found : Id used 100");
    }
}

package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
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
public class TradeServiceTests {
    @Autowired
    private TradeService tradeService;
    @Mock
    private TradeRepository tradeRepository;

    @BeforeEach
    public void setup() {
        tradeRepository = mock(TradeRepository.class);
    }

    @Test
    void findById_Ok() {
        //GIVEN
        Trade tradeExpected = new Trade();
        tradeExpected.setTradeId(1);
        tradeExpected.setAccount("account1");
        tradeExpected.setType("type1");
        tradeExpected.setBuyQuantity(1.0);

        when(tradeRepository.findById(1)).thenReturn(Optional.of(tradeExpected));

        //WHEN
        Trade tradeActual = tradeService.findById(1);

        //THEN
        assertThat(tradeExpected).usingRecursiveComparison()
                .isEqualTo(tradeActual);
    }

    @Test
    void findById_Trade_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //id = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            tradeService.findById(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Trade not found : Id used 10");
    }

    @Test
    void update_Trade_Not_Found() {
        //GIVEN
        Trade tradeNotInDB = new Trade();
        tradeNotInDB.setTradeId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            tradeService.update(tradeNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Trade to update not found : Id used 100");
    }

    @Test
    void delete_Trade_Not_Found() {
        //GIVEN
        Integer idNotInDb = 100;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            tradeService.delete(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Trade to delete not found : Id used 100");
    }
}

package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
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
public class CurvePointServiceTests {
    @Autowired
    private CurvePointService curvePointService;
    @Mock
    private CurvePointRepository curvePointRepository;

    @BeforeEach
    public void setup() {
        curvePointRepository = mock(CurvePointRepository.class);
    }

    @Test
    void findById_Ok() {
        //GIVEN
        CurvePoint curvePointExpected = new CurvePoint();
        curvePointExpected.setId(1);
        curvePointExpected.setTerm(1.0);
        curvePointExpected.setValue(1.0);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePointExpected));

        //WHEN
        CurvePoint curvePointActual = curvePointService.findById(1);

        //THEN
        assertThat(curvePointExpected).usingRecursiveComparison()
                .isEqualTo(curvePointActual);
    }

    @Test
    void findById_CurvePoint_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //id = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            curvePointService.findById(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CurvePoint not found : Id used 10");
    }

    @Test
    void update_CurvePoint_Not_Found() {
        //GIVEN
        CurvePoint curvePointNotInDB = new CurvePoint();
        curvePointNotInDB.setId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            curvePointService.update(curvePointNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CurvePoint to update not found : Id used 100");
    }

    @Test
    void delete_CurvePoint_Not_Found() {
        //GIVEN
        Integer idNotInDb = 100;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            curvePointService.delete(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CurvePoint to delete not found : Id used 100");
    }
}

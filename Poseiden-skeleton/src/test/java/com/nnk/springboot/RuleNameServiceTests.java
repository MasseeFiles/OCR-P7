package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleService;
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
public class RuleNameServiceTests {
    @Autowired
    private RuleService ruleNameService;
    @Mock
    private RuleNameRepository ruleNameRepository;

    @BeforeEach
    public void setup() {
        ruleNameRepository = mock(RuleNameRepository.class);
    }

    @Test
    void findById_Ok() {
        //GIVEN
        RuleName ruleNameExpected = new RuleName();
        ruleNameExpected.setRuleId(1);
        ruleNameExpected.setName("name1");
        ruleNameExpected.setDescription("description1");
        ruleNameExpected.setJson("json1");
        ruleNameExpected.setTemplate("template1");
        ruleNameExpected.setSqlStr("sqlStr1");
        ruleNameExpected.setSqlPart("sqlPart1");

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleNameExpected));

        //WHEN
        RuleName ruleNameActual = ruleNameService.findById(1);

        //THEN
        assertThat(ruleNameExpected).usingRecursiveComparison()
                .isEqualTo(ruleNameActual);
    }

    @Test
    void findById_RuleName_Not_Found() {
        //GIVEN
        Integer idNotInDb = 10; //id = 10 non present dans DBDataInitializerTest

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ruleNameService.findById(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("RuleName not found : Id used 10");
    }

    @Test
    void update_RuleName_Not_Found() {
        //GIVEN
        RuleName ruleNameNotInDB = new RuleName();
        ruleNameNotInDB.setRuleId(100);

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ruleNameService.update(ruleNameNotInDB);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("RuleName to update not found : Id used 100");
    }

    @Test
    void delete_RuleName_Not_Found() {
        //GIVEN
        Integer idNotInDb = 100;

        //WHEN

        //THEN
        assertThatThrownBy(() -> {
            ruleNameService.delete(idNotInDb);
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("RuleName to delete not found : Id used 100");
    }
}
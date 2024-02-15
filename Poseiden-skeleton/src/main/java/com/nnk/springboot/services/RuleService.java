package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RuleService {
    List<RuleName> findAll();

    RuleName findById(Integer id);

    void add(RuleName ruleName);

    void update(RuleName ruleName);

    void delete(Integer id);
}

package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RuleService {
    List<Rule> findAll();

    Rule findById(Integer id);

    void save(Rule rule);

    void update(Rule rule);

    void delete(Integer id);
}

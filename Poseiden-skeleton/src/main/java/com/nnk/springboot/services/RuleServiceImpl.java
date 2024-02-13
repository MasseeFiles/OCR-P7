package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Override
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    @Override
    public RuleName findById(Integer id) {
        RuleName ruleNameToFind = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RuleName not found : Id used " + id));

        return ruleNameToFind;
    }

    @Override
    public void add(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    @Override
    public void update(RuleName ruleName) {
        RuleName ruleNameToUpdate = ruleNameRepository.findById(ruleName.getRuleId())
                .orElseThrow(() -> new RuntimeException("RuleName to update not found : Id used " + ruleName.getRuleId()));

        ruleNameRepository.delete(ruleNameToUpdate);
        ruleNameRepository.save(ruleName);
    }

    @Override
    public void delete(Integer id) {
        RuleName ruleNameToDelete = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RuleName to delete not found : Id used " + id));

        ruleNameRepository.delete(ruleNameToDelete);
    }
}

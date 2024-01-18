//package com.nnk.springboot.services;
//
//import com.nnk.springboot.domain.Rule;
//import com.nnk.springboot.repositories.RuleNameRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class RuleServiceImpl implements RuleService {
//    @Autowired
//    private RuleNameRepository ruleNameRepository;
//
//    @Override
//    public List<Rule> findAll() {
//        return ruleNameRepository.findAll();
//    }
//
//    @Override
//    public Rule findById(Integer id) {
//        Rule ruleToFind = ruleNameRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Rule not found : Id used " + id));
//
//        return ruleToFind;
//    }
//
//    @Override
//    public void save(Rule rule) {
//        ruleNameRepository.save(rule);
//    }
//
//    @Override
//    public void update(Rule rule) {
//        Rule ruleToUpdate = ruleNameRepository.findById(rule.getRuleId())
//                .orElseThrow(() -> new RuntimeException("Rule to update not found : Id used " + rule.getRuleId()));
//
//        ruleNameRepository.delete(ruleToUpdate);
//        ruleNameRepository.save(rule);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        Rule ruleToDelete = ruleNameRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Rule to delete not found : Id used " + id));
//
//        ruleNameRepository.delete(ruleToDelete);
//    }
//}

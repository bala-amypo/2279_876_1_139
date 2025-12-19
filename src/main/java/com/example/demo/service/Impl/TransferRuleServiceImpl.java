package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
class TransferRuleServiceImpl implements TransferRuleService {

    private final TransferRuleRepository ruleRepository;

    public TransferRuleServiceImpl(TransferRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = getRuleById(id);
        existing.setMinOverlapPercentage(rule.getMinOverlapPercentage());
        existing.setMaxCreditDifference(rule.getMaxCreditDifference());
        existing.setActive(rule.isActive());
        return ruleRepository.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return ruleRepository.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId)
                .map(List::of)
                .orElse(List.of());
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = getRuleById(id);
        rule.setActive(false);
        ruleRepository.save(rule);
    }
}

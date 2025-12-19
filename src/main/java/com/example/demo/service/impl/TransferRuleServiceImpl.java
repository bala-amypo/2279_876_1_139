package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    // REQUIRED FIELD NAMES
    private final TransferRuleRepository repo;
    private final UniversityRepository univRepo;

    public TransferRuleServiceImpl(
            TransferRuleRepository repo,
            UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public TransferRule createRule(TransferRule rule) {
        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        rule.setId(id);
        return repo.save(rule);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        repo.findById(id).ifPresent(r -> {
            r.setActive(false);
            repo.save(r);
        });
    }
}

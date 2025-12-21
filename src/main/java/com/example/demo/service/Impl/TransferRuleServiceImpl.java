package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.entity.University;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {

    @Autowired
    private TransferRuleRepository repo;

    @Autowired
    private UniversityRepository univRepo;

    @Override
    public TransferRule createRule(TransferRule rule) {
        if (rule.getMinimumOverlapPercentage() < 0 || rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("Overlap must be 0-100");
        }
        University source = univRepo.findById(rule.getSourceUniversity().getId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
        University target = univRepo.findById(rule.getTargetUniversity().getId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
        rule.setSourceUniversity(source);
        rule.setTargetUniversity(target);
        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceId, Long targetId) {
        return repo.findBySourceUniversityIdAndTargetUniversityId(sourceId, targetId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}
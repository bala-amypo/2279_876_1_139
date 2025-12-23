package com.example.demo.service.impl;

import com.example.demo.entity.TransferRule;
import com.example.demo.repository.TransferRuleRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.TransferRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransferRuleServiceImpl implements TransferRuleService {
    
    private TransferRuleRepository repo;
    private UniversityRepository univRepo;

    @Override
    public TransferRule createRule(TransferRule rule) {
        if (rule.getMinimumOverlapPercentage() < 0 || rule.getMinimumOverlapPercentage() > 100) {
            throw new IllegalArgumentException("Minimum overlap percentage must be between 0-100");
        }
        if (rule.getCreditHourTolerance() != null && rule.getCreditHourTolerance() < 0) {
            throw new IllegalArgumentException("Credit hour tolerance must be >= 0");
        }
        
        if (rule.getSourceUniversity() != null) {
            univRepo.findById(rule.getSourceUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Source university not found"));
        }
        if (rule.getTargetUniversity() != null) {
            univRepo.findById(rule.getTargetUniversity().getId())
                .orElseThrow(() -> new RuntimeException("Target university not found"));
        }
        
        return repo.save(rule);
    }

    @Override
    public TransferRule updateRule(Long id, TransferRule rule) {
        TransferRule existing = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
        existing.setMinimumOverlapPercentage(rule.getMinimumOverlapPercentage());
        existing.setCreditHourTolerance(rule.getCreditHourTolerance());
        return repo.save(existing);
    }

    @Override
    public TransferRule getRuleById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
    }

    @Override
    public List<TransferRule> getRulesForUniversities(Long sourceUniversityId, Long targetUniversityId) {
        return repo.findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(sourceUniversityId, targetUniversityId);
    }

    @Override
    public void deactivateRule(Long id) {
        TransferRule rule = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Transfer rule not found"));
        rule.setActive(false);
        repo.save(rule);
    }
}
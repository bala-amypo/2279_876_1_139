package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class TransferRuleController {
    
    @Autowired
    private TransferRuleService ruleService;

    @PostMapping
    public ResponseEntity<TransferRule> createRule(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRule> getRule(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @GetMapping("/universities")
    public ResponseEntity<List<TransferRule>> getRulesForUniversities(
            @RequestParam Long sourceUniversityId, 
            @RequestParam Long targetUniversityId) {
        return ResponseEntity.ok(ruleService.getRulesForUniversities(sourceUniversityId, targetUniversityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferRule> updateRule(@PathVariable Long id, @RequestBody TransferRule rule) {
        return ResponseEntity.ok(ruleService.updateRule(id, rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateRule(@PathVariable Long id) {
        ruleService.deactivateRule(id);
        return ResponseEntity.ok().build();
    }
}
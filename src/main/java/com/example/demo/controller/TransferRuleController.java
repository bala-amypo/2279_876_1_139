package com.example.demo.controller;

import com.example.demo.entity.TransferRule;
import com.example.demo.service.TransferRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer-rules")
@Tag(name = "Transfer Rule")
public class TransferRuleController {

    private final TransferRuleService service;

    public TransferRuleController(TransferRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransferRule> create(@RequestBody TransferRule rule) {
        return ResponseEntity.ok(service.create(rule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferRule> update(@PathVariable Long id, @RequestBody TransferRule rule) {
        return ResponseEntity.ok(service.update(id, rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRule> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/pair/{sourceId}/{targetId}")
    public ResponseEntity<?> getByPair(@PathVariable Long sourceId, @PathVariable Long targetId) {
        return ResponseEntity.ok(service.getByPair(sourceId, targetId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivate(id);
        return ResponseEntity.ok().build();
    }
}

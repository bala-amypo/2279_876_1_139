// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.web.bind.annotation.*;

// import com.example.demo.entity.TransferRule;
// import com.example.demo.service.TransferRuleService;

// import io.swagger.v3.oas.annotations.tags.Tag;

// @RestController
// @RequestMapping("/api/transfer-rules")
// @Tag(name = "Transfer Rules")
// public class TransferRuleController {

//     private final TransferRuleService service;

//     public TransferRuleController(TransferRuleService service) {
//         this.service = service;
//     }

//     @PostMapping
//     public TransferRule create(@RequestBody TransferRule rule) {
//         return service.createRule(rule);
//     }

//     @PutMapping("/{id}")
//     public TransferRule update(@PathVariable Long id, @RequestBody TransferRule rule) {
//         return service.updateRule(id, rule);
//     }

//     @GetMapping("/{id}")
//     public TransferRule getById(@PathVariable Long id) {
//         return service.getRuleById(id);
//     }

//     @GetMapping("/pair/{sourceId}/{targetId}")
//     public List<TransferRule> getByUniversities(
//             @PathVariable Long sourceId,
//             @PathVariable Long targetId) {
//         return service.getRulesForUniversities(sourceId, targetId);
//     }

//     @PutMapping("/{id}/deactivate")
//     public void deactivate(@PathVariable Long id) {
//         service.deactivateRule(id);
//     }
// }

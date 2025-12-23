package com.example.demo.repository;

import com.example.demo.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {
    List<TransferRule> findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(Long sourceUniversityId, Long targetUniversityId);
}
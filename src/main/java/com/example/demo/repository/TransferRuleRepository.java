package com.example.transferportal.repository;

import com.example.transferportal.entity.TransferRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRuleRepository extends JpaRepository<TransferRule, Long> {

    Optional<TransferRule> findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
            Long sourceId,
            Long targetId
    );
}

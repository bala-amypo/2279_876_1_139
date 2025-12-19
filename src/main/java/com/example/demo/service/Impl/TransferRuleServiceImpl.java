// =============================
// 4. TransferRuleServiceImpl
// =============================
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

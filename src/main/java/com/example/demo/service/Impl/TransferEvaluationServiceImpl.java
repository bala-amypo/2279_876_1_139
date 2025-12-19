// =============================
// 1. UniversityServiceImpl
// =============================
@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUniversity(University univ) {
        return universityRepository.save(univ);
    }

    @Override
    public University updateUniversity(Long id, University univ) {
        University existing = getUniversityById(id);
        existing.setName(univ.getName());
        existing.setActive(univ.isActive());
        return universityRepository.save(existing);
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University not found"));
    }

    @Override
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Override
    public void deactivateUniversity(Long id) {
        University university = getUniversityById(id);
        university.setActive(false);
        universityRepository.save(university);
    }
}



// =============================
// 5. TransferEvaluationServiceImpl
// =============================
@Service
class TransferEvaluationServiceImpl implements TransferEvaluationService {

    private final CourseRepository courseRepository;
    private final CourseContentTopicRepository topicRepository;
    private final TransferRuleRepository ruleRepository;
    private final TransferEvaluationResultRepository resultRepository;

    public TransferEvaluationServiceImpl(
            CourseRepository courseRepository,
            CourseContentTopicRepository topicRepository,
            TransferRuleRepository ruleRepository,
            TransferEvaluationResultRepository resultRepository) {
        this.courseRepository = courseRepository;
        this.topicRepository = topicRepository;
        this.ruleRepository = ruleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public TransferEvaluationResult evaluateTransfer(Long sourceCourseId, Long targetCourseId) {

        Course source = courseRepository.findById(sourceCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Source course not found"));
        Course target = courseRepository.findById(targetCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("Target course not found"));

        if (!source.isActive() || !target.isActive()) {
            throw new ResourceNotFoundException("Inactive course found");
        }

        List<CourseContentTopic> sourceTopics = topicRepository.findByCourseId(sourceCourseId);
        List<CourseContentTopic> targetTopics = topicRepository.findByCourseId(targetCourseId);

        Set<String> sourceSet = new HashSet<>();
        sourceTopics.forEach(t -> sourceSet.add(t.getTopicName()));

        Set<String> targetSet = new HashSet<>();
        targetTopics.forEach(t -> targetSet.add(t.getTopicName()));

        sourceSet.retainAll(targetSet);

        double overlapPercentage = targetTopics.isEmpty()
                ? 0
                : (sourceSet.size() * 100.0) / targetTopics.size();

        TransferRule rule = ruleRepository
                .findBySourceUniversityIdAndTargetUniversityIdAndActiveTrue(
                        source.getUniversity().getId(),
                        target.getUniversity().getId()
                )
                .orElse(null);

        TransferEvaluationResult result = new TransferEvaluationResult();
        result.setSourceCourse(source);
        result.setTargetCourse(target);

        if (rule == null) {
            result.setEligible(false);
            result.setNotes("No active transfer rule");
        } else {
            boolean eligible = overlapPercentage >= rule.getMinOverlapPercentage()
                    && Math.abs(source.getCredits() - target.getCredits()) <= rule.getMaxCreditDifference();

            result.setEligible(eligible);
            result.setNotes(eligible ? "Eligible for transfer" : "Transfer conditions not met");
        }

        return resultRepository.save(result);
    }

    @Override
    public TransferEvaluationResult getEvaluationById(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found"));
    }

    @Override
    public List<TransferEvaluationResult> getEvaluationsForCourse(Long courseId) {
        return resultRepository.findBySourceCourseId(courseId);
    }
}


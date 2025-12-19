
@Service
class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = getCourseById(id);
        existing.setCourseName(course.getCourseName());
        existing.setCredits(course.getCredits());
        existing.setActive(course.isActive());
        return courseRepository.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return courseRepository.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course course = getCourseById(id);
        course.setActive(false);
        courseRepository.save(course);
    }
}

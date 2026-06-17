package at.spengergasse.service;

import at.spengergasse.domain.Course;
import at.spengergasse.domain.CourseException;
import at.spengergasse.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ComputerCourseService {

   private final CourseRepository repository;

    public ComputerCourseService(CourseRepository repository) {
        this.repository = repository;
        if (repository.count() == 0)
            FillTestData();
    }




    public void FillTestData() {

        repository.save (new Course(LocalDate.of(2026, 1, 10), "Git & GitHub", "Beginner", 3, 79.0, false));
        repository.save(new Course(LocalDate.of(2026, 1, 15), "HTML & CSS Basics", "Beginner", 5, 149.0, false));
        repository.save(new Course(LocalDate.of(2026, 1, 20), "Java Fundamentals", "Beginner", 8, 199.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 5), "Java OOP", "Intermediate", 10, 299.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 10), "PostgreSQL", "Intermediate", 8, 249.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 15), "Frontend Development", "Intermediate", 12, 349.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 1), "Spring Boot", "Advanced", 15, 499.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 5), "Docker & Containers", "Advanced", 8, 399.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 10), "DevOps Fundamentals", "Advanced", 12, 599.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 15), "Clean Architecture", "Advanced", 10, 699.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 20), "Microservices", "Advanced", 14, 799.0, true));
        repository.save(new Course(LocalDate.of(2026, 4, 1), "Python Basics", "Beginner", 6, 169.0, false));
        repository.save(new Course(LocalDate.of(2026, 4, 5), "Python OOP", "Intermediate", 8, 269.0, true));
        repository.save(new Course(LocalDate.of(2026, 4, 10), "Python Automation", "Intermediate", 10, 319.0, true));
        repository.save(new Course(LocalDate.of(2026, 4, 15), "Data Structures", "Intermediate", 9, 289.0, true));
        repository.save(new Course(LocalDate.of(2026, 4, 20), "Algorithms", "Advanced", 12, 549.0, true));
        repository.save(new Course(LocalDate.of(2026, 5, 1), "Vaadin Basics", "Intermediate", 8, 299.0, true));
        repository.save(new Course(LocalDate.of(2026, 5, 5), "Vaadin Advanced", "Advanced", 12, 499.0, true));
        repository.save(new Course(LocalDate.of(2026, 5, 10), "REST APIs", "Intermediate", 8, 249.0, true));
        repository.save(new Course(LocalDate.of(2026, 5, 15), "JUnit Testing", "Intermediate", 6, 199.0, false));
        repository.save(new Course(LocalDate.of(2026, 5, 20), "Design Patterns", "Advanced", 10, 649.0, true));
        repository.save(new Course(LocalDate.of(2026, 6, 1), "Arduino Basics", "Beginner", 4, 99.0, false));
        repository.save(new Course(LocalDate.of(2026, 6, 5), "Electronics", "Beginner", 6, 149.0, false));
        repository.save(new Course(LocalDate.of(2026, 6, 10), "Computer Networks", "Intermediate", 10, 299.0, true));
        repository.save(new Course(LocalDate.of(2026, 6, 15), "Cyber Security", "Advanced", 12, 899.0, true));
        repository.save(new Course(LocalDate.of(2026, 6, 20), "Cloud Computing", "Advanced", 15, 999.0, true));
    }


    public ArrayList <Course>  findAll() {
        ArrayList<Course> clone;

        clone = (ArrayList<Course>) repository.findAll();

        return clone;
    }


    @Override
    public String toString() {
        return repository.findAll().stream()
                .map(course -> course.toString())
                .collect(Collectors.joining("\n"));
    }

    public void removeAllLessons() {
        repository.deleteAll();
    }

    public void add10Lessons() {
        repository.save(new Course(LocalDate.of(2026, 1, 10), "Git & GitHub", "Beginner", 3, 79.0, false));
        repository.save(new Course(LocalDate.of(2026, 1, 15), "HTML & CSS Basics", "Beginner", 5, 149.0, false));
        repository.save(new Course(LocalDate.of(2026, 1, 20), "Java Fundamentals", "Beginner", 8, 199.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 5), "Java OOP", "Intermediate", 10, 299.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 10), "PostgreSQL", "Intermediate", 8, 249.0, true));
        repository.save(new Course(LocalDate.of(2026, 2, 15), "Frontend Development", "Intermediate", 12, 349.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 1), "Spring Boot", "Advanced", 15, 499.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 5), "Docker & Containers", "Advanced", 8, 399.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 10), "DevOps Fundamentals", "Advanced", 12, 599.0, true));
        repository.save(new Course(LocalDate.of(2026, 3, 15), "Clean Architecture", "Advanced", 10, 699.0, true));

    }

    public void add10Euro() {
        for(Course c : repository.findAll()){
            c.setPrice(c.getPrice()+10);
            repository.save(c);
        }
    }

    public  void remove1Lesson(Long courseId) {
        if( courseId == null)
            throw new CourseException("Course ID does not exist!");
        if( !repository.existsById(courseId))
            throw  new CourseException("Course ID does not exist!");
        repository.deleteById(courseId);

    }

    public void add1Lesson(Long courseId) {
        if( courseId == null)
            throw new CourseException("Course ID does not exist!");
        for (Course c : repository.findAll()){
            if (c.getCourseId().equals(courseId))
                c.setLessons(c.getLessons() +1);
            repository.save(c);
            return;
        }
    }

    public void addNewCourse(Course course) {
        if(course == null){
            throw new CourseException("Error");
        }
        repository.save(course);
    }


    public void editCourse(Course course) {
        if (course == null)
            throw new CourseException("Course is null!");

        repository.save(course);

    }
}

package at.spengergasse.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;


@Getter
@Setter
@EqualsAndHashCode(of ="courseId", callSuper = false)
@Entity
public class CourseDb {
    @Id
    private Long      courseId;
    private LocalDate startDate;
    private String    courseName;
    private String    courseLevel;
    private Integer   lessons;
    private Double    price;
    private Boolean certificate;

    public CourseDb(){
        setStartDate(LocalDate.now());
        setCourseName("Git & GitHub");
        setCourseLevel("Beginner");
        setLessons(3);
        setPrice(79.0);
        setCertificate(false);
    }

    public CourseDb(LocalDate startDate, String courseName, String courseLevel, Integer lessons, Double price, Boolean certificate) {
        setCourseId();
        setStartDate(startDate);
        setCourseName(courseName);
        setCourseLevel(courseLevel);
        setLessons(lessons);
        setPrice(price);
        setCertificate(certificate);
    }

    public CourseDb(Long courseId, LocalDate startDate, String courseName, String courseLevel, Integer lessons, Double price, Boolean certificate) {
        setCourseId(courseId);
        setStartDate(startDate);
        setCourseName(courseName);
        setCourseLevel(courseLevel);
        setLessons(lessons);
        setPrice(price);
        setCertificate(certificate);
    }

    @Override
    public CourseDb clone (){
        return new CourseDb(courseId,startDate,courseName,courseLevel,lessons,price,certificate);
    }

    private static final AtomicLong sequence = new AtomicLong(10000);


    public void setCourseId(){
        courseId = sequence.getAndIncrement();
    }
}


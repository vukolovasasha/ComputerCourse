package at.spengergasse.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;


@Getter
@Setter
@EqualsAndHashCode(of ="courseId", callSuper = false)
@Entity
public class Course {
    @Id
    private Long      courseId;
    private LocalDate startDate;
    private String    courseName;
    private String    courseLevel;
    private Integer   lessons;
    private Double    price;
    private Boolean certificate;

    public Course(){

    }

    public Course(LocalDate startDate, String courseName, String courseLevel, Integer lessons, Double price, Boolean certificate) {
        setCourseId();
        setStartDate(startDate);
        setCourseName(courseName);
        setCourseLevel(courseLevel);
        setLessons(lessons);
        setPrice(price);
        setCertificate(certificate);
    }

    public Course(Long courseId, LocalDate startDate, String courseName, String courseLevel, Integer lessons, Double price, Boolean certificate) {
        setCourseId(courseId);
        setStartDate(startDate);
        setCourseName(courseName);
        setCourseLevel(courseLevel);
        setLessons(lessons);
        setPrice(price);
        setCertificate(certificate);
    }
    private static final String[] levels = {"Beginner", "Intermediate", "Advanced"
    };

    public void setCourseLevel(String courseLevel) {

        if (!Arrays.asList(levels).contains(courseLevel))
            throw new CourseException(
                    "Wrong level, must be: " + Arrays.toString(levels));
        this.courseLevel = courseLevel;
    }

    public void setPrice(Double price) {

        if (price < 50)
            throw new CourseException("The min. price is 50.0 Eur");

        if (price > 2000)
            throw new CourseException("The max. price is 2000.0 Eur");

        this.price = price;
    }

    @Override
    public Course clone (){
        return new Course(courseId,startDate,courseName,courseLevel,lessons,price,certificate);
    }

    private static final AtomicLong sequence = new AtomicLong(10000);


    public void setCourseId(){
        courseId = sequence.getAndIncrement();
    }
}


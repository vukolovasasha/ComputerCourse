package at.spengergasse.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotNull(message = "Course date is required!")
    @PastOrPresent(message = "Course can be only in past or present")
    private LocalDate startDate;
    @NotBlank(message = "Course name is required!")
    @Size(min = 3, max = 100, message = "name must be from 3 to 100 character")
    private String courseName;
    @NotNull(message = "Course level is required!")
    @Pattern(regexp = "Beginner|Intermediate|Advanced", message = "Beginner,Intermediate, Advanced")
    private String courseLevel;
    @NotNull(message = "Number of lessons is required!")
    @Min(value = 1, message = "min 1 lesson")
    private Integer lessons;
    @NotNull(message = "Course price is required!")
    @DecimalMin(value = "50.0", message = "min 50 euro")
    @DecimalMax(value = "2000.0", message = "max 2000 euro")
    private Double price;
    @NotNull(message = "Information about certificate is required!")
    private Boolean certificate;

    public Course() {
        setStartDate(LocalDate.now());
        setCourseName("Java Fundamentals");
        setCourseLevel("Beginner");
        setLessons(8);
        setPrice(199.0);
        setCertificate(true);
    }

    public Course(LocalDate startDate, String courseName, String courseLevel, Integer lessons, Double price, Boolean certificate) {
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


    /*
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

     */

    @Override
    public Course clone() {
        return new Course(courseId, startDate, courseName, courseLevel, lessons, price, certificate);
    }
}







package at.spengergasse.views.lessons;

import at.spengergasse.domain.Course;
import at.spengergasse.domain.CourseException;
import at.spengergasse.service.ComputerCourseService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.textfield.TextField;

import java.time.LocalDate;

import static com.vaadin.copilot.shaded.io.netty.handler.codec.http.HttpHeaders.setHeader;

@PageTitle("Lessons")
@Route("lessons")
@Menu(order = 1, icon = LineAwesomeIconUrl.GRADUATION_CAP_SOLID)
public class LessonsView extends VerticalLayout {
    private final Button buttonRemoveAllLessons = new Button("Remove all");
    private final Button buttonAdd10Lessons = new Button("Add 10 lessons");
    private final Button buttonAdd10Euro = new Button("+10 Euro");
    private final Button buttonAddCourse = new Button("Add new Course");
    private final Grid<Course> grid = new Grid<>(Course.class,false);
    private final ComputerCourseService computerCourseService;

    public LessonsView(@Autowired ComputerCourseService computerCourseService) {
        this.computerCourseService = computerCourseService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();

        setSpacing(true);
        setSizeFull();

        grid.setSizeFull();
        grid.setWidthFull();

        grid.getStyle()
                .set("--vaadin-grid-cell-white-space", "normal");

        buttonRemoveAllLessons.addClickListener((ClickEvent<Button>event) -> removeAllLessons());
        buttonAdd10Lessons.addClickListener((ClickEvent<Button>event) -> add10Lessons());
        buttonAdd10Euro.addClickListener((ClickEvent<Button>event)-> buttonAdd10Euro());
        buttonAddCourse.addClickListener(buttonClickEvent -> buttonAddCourse());

        add(new HorizontalLayout(buttonRemoveAllLessons, buttonAdd10Lessons, buttonAdd10Euro, buttonAddCourse));



        grid.addColumn(course -> course.getCourseId())
                        .setHeader("Course ID")
                                .setSortable(true);

        grid.addColumn(course -> course.getStartDate())
                .setHeader("Course Date")
                .setAutoWidth(true)
                .setSortable(true);

        Image l = new Image("images/logo.png", "Company logo");
        l.setWidth("30px");
        HorizontalLayout headerType = new HorizontalLayout(l, new Span("Name"));
        grid.addColumn(course -> course.getCourseName())
                .setHeader(headerType)
                .setWidth("200px")
                .setFlexGrow(1)
                .setSortable(true);

        grid.addColumn(course -> course.getCourseLevel())
                .setHeader("Level")
                .setSortable(true);

        grid.addColumn(course -> course.getLessons())
                .setHeader("Lesson")
                .setSortable(true);

        grid.addColumn(course -> course.getPrice())
                .setHeader("Course Price")
                .setAutoWidth(true)
                .setSortable(true);

        grid.addColumn(course -> course.getCertificate()  ? "Yes" : "Nope")
                .setHeader("Certificate")
                .setSortable(true);

        grid.addComponentColumn(course -> {
            Checkbox certificate =  new Checkbox(course.getCertificate());
            certificate.setReadOnly(true);
            return certificate;

        })
                .setHeader("Certificate")
                .setSortable(true);


        grid.addComponentColumn(course ->{
                    Button delete = new Button("Delete");
                    delete.addClickListener(e -> remove1Lesson(course.getCourseId()));
                    return delete;
                })
                        .setHeader("Delete course")
                                .setSortable(false);

        grid.addComponentColumn(course -> {
            Button add1Lesson = new Button("Add 1 lesson");
            add1Lesson.addClickListener(e -> add1Lesson(course.getCourseId()));
            return  add1Lesson;
        })
                        .setHeader("Add 1 lesson")
                                .setSortable(false);


        add(grid);
        expand(grid);
        reload();
    }

    private void buttonAddCourse() {
        Dialog dialog;
        dialog = new Dialog();
        dialog.setHeaderTitle("Add new Course");

        TextField courseId = new TextField("Course ID");
        DatePicker startDate = new DatePicker("Course start date");
        TextField courseName = new TextField("Course name");
        ComboBox courseLevel = new ComboBox("Course Level");
        courseLevel.setItems("Beginner", "Intermediate", "Advanced");
        NumberField price = new NumberField("Price");
        IntegerField lessons = new IntegerField("Number of lessons");
        Checkbox certificate = new Checkbox("Certificate");

        BeanValidationBinder<Course> binder = new BeanValidationBinder<>(Course.class);

        binder.forField(startDate)
                .bind("startDate");
        binder.forField(courseName)
                .bind("courseName");
        binder.forField(courseLevel)
                .bind("courseLevel");
        binder.forField(price)
                .bind("price");
        binder.forField(lessons)
                .bind("lessons");
        binder.forField(certificate)
                .bind("certificate");

        Course course = new Course();
        binder.setBean(course);

        courseId.setValue(""+course.getCourseId());
        courseId.setReadOnly(true);

        VerticalLayout formLayout = new VerticalLayout(
                courseId,startDate,courseName,courseLevel,price,lessons,certificate
        );

        Button buttonOK = new Button("OK");
        Button buttonCansel = new Button("Cancel");

        buttonOK.addClickListener(buttonClickEvent -> {
            try {
                if(binder.validate().isOk()){
                    computerCourseService.addNewCourse(course);
                    dialog.close();
                    reload();
                    Notification.show("New course added");

            }
                else
                    Notification.show("Check your input!");
            }
            catch (CourseException e){
                Notification.show(e.getMessage());
            }
        });

        buttonCansel.addClickListener(buttonClickEvent -> {
            dialog.close();
        });

        dialog.add(formLayout);
        HorizontalLayout buttons = new HorizontalLayout(
                buttonOK,
                buttonCansel
        );

        buttons.setWidthFull();
        buttons.setJustifyContentMode(
                JustifyContentMode.CENTER
        );

        dialog.getFooter().add(buttons);
        dialog.open();
    }

    private void add1Lesson(Long courseId) {
        try {
            computerCourseService.add1Lesson(courseId);
            reload();
        }
        catch (CourseException e){
            Notification.show(e.getMessage());
        }
    }

    private void remove1Lesson(Long courseId) {
        try {
            computerCourseService.remove1Lesson(courseId);
            reload();

        }
        catch (CourseException e){
            Notification.show(e.getMessage());
            reload();
        }
    }

    private void buttonAdd10Euro() {
        try {
            computerCourseService.add10Euro();
            reload();
        }
        catch (CourseException e){
            Notification.show(e.getMessage());
        }
    }

    private void add10Lessons() {
        try {
            computerCourseService.add10Lessons();
            buttonRemoveAllLessons.setEnabled(true);
            reload();
        }
        catch (CourseException e){
            Notification.show(e.getMessage());
        }

    }

    private void removeAllLessons() {
        try {
            computerCourseService.removeAllLessons();
            buttonRemoveAllLessons.setEnabled(false);
            reload();
        }
        catch (CourseException e){
            Notification.show(e.getMessage());
        }
    }

    private void reload() {
        grid.setItems(computerCourseService.findAll());
    }


}
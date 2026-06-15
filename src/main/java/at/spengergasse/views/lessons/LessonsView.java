package at.spengergasse.views.lessons;

import at.spengergasse.domain.Course;
import at.spengergasse.domain.CourseException;
import at.spengergasse.service.ComputerCourseService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;

import java.time.LocalDate;

import static com.vaadin.copilot.shaded.io.netty.handler.codec.http.HttpHeaders.setHeader;

@PageTitle("Lessons")
@Route("lessons")
@Menu(order = 1, icon = LineAwesomeIconUrl.GRADUATION_CAP_SOLID)
public class LessonsView extends VerticalLayout {
    private final Button buttonRemoveAllLessons = new Button("Remove all");
    private final Button buttonAdd10Lessons = new Button("Add 10 lessons");
    private final Button buttonAdd10Euro = new Button("+10 Euro");
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

        add(new HorizontalLayout(buttonRemoveAllLessons, buttonAdd10Lessons, buttonAdd10Euro));



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


        add(grid);
        expand(grid);
        reload();
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
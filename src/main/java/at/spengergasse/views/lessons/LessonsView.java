package at.spengergasse.views.lessons;

import at.spengergasse.domain.Course;
import at.spengergasse.service.ComputerCourseService;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

import com.vaadin.flow.component.button.Button;

@PageTitle("Lessons")
@Route("lessons")
@Menu(order = 1, icon = LineAwesomeIconUrl.GRADUATION_CAP_SOLID)
public class LessonsView extends VerticalLayout {
    private final Button buttonRemoveAllLessons = new Button("Remove all");
    private final Button buttonAdd10Lessons = new Button("Add 10 lessons");
    private final Grid<Course> grid = new Grid<>(Course.class,true);
    private final ComputerCourseService computerCourseService;

    public LessonsView(@Autowired ComputerCourseService computerCourseService) {
        this.computerCourseService = computerCourseService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();

        buttonRemoveAllLessons.addClickListener((ClickEvent<Button>event) -> removeAllLessons());
        buttonAdd10Lessons.addClickListener((ClickEvent<Button>event) -> add10Lessons());

        add(new HorizontalLayout(buttonRemoveAllLessons, buttonAdd10Lessons));

        add(grid);
        reload();
    }

    private void add10Lessons() {
        computerCourseService.add10Lessons();
        reload();
    }

    private void removeAllLessons() {
        computerCourseService.removeAllLessons();
        reload();
    }

    private void reload() {
        grid.setItems(computerCourseService.findAll());
    }


}
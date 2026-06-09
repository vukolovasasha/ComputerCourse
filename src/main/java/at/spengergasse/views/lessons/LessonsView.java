package at.spengergasse.views.lessons;

import at.spengergasse.domain.Course;
import at.spengergasse.service.ComputerCourseService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Lessons")
@Route("lessons")
@Menu(order = 1, icon = LineAwesomeIconUrl.GRADUATION_CAP_SOLID)
public class LessonsView extends VerticalLayout {
    private final Grid<Course> grid = new Grid<>(Course.class,true);
    private final ComputerCourseService computerCourseService;

    public LessonsView(@Autowired ComputerCourseService computerCourseService) {
        this.computerCourseService = computerCourseService;

        setSpacing(true);
        setSizeFull();
        grid.setSizeFull();
        add(grid);
        reload();
    }

    private void reload() {
        grid.setItems(computerCourseService.findAll());
    }


}
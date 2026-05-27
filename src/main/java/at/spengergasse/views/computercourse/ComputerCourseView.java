package at.spengergasse.views.computercourse;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Home")
@Route("home")
@Menu(order = 0, icon = LineAwesomeIconUrl.HOME_SOLID)
public class ComputerCourseView extends VerticalLayout {

    public ComputerCourseView() {
        setSpacing(false);

        setAlignItems(Alignment.CENTER);

    VerticalLayout header = getHeader();

    Image img = new Image("images/logo.png", "Vibe Coding logo");
        img.setWidth("220px");

    Paragraph description = new Paragraph(
            "Still coding the old way?   " +
                    "Join our Vibe Coding courses — where bugs become features, prompts become projects, and " +
                    " ‘it works on my machine’ becomes a lifestyle." +
                    " We don’t just teach programming — we teach you to code with vibes."
    );
        description.setWidth("500px");
        description.getStyle()
                .set("font-size", "22px")
                .set("line-height", "1.6")
                .set("text-align", "left");

    H3 name = new H3("Vibe Coding GmbH");
    H3 street = new H3("Spengergasse 20");
    H3 city = new H3("1050 Wien");

    add(header, img, description, name, street, city);

}


    public static VerticalLayout getHeader(){

        VerticalLayout header;
        header = new VerticalLayout();

        H1 companyName = new H1("Vibe Сoding");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "4rem")
                .set("margin", "0");

        H2 subName = new H2("... We’ll teach you how to vibe code ...");
        subName.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        header.add(companyName,subName);

        return header;

    }
    }

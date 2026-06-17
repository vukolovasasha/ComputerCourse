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
        setMinHeight("100vh");
        setWidthFull();

        setSpacing(false);
        setPadding(false);
        setAlignItems(Alignment.CENTER);

        getStyle()
                .set("background", "#f5f7fb")
                .set("font-family", "Arial, sans-serif");
        getElement().executeJs(
                "document.documentElement.style.background='#f5f7fb';" +
                        "document.body.style.background='#f5f7fb';"
        );

        VerticalLayout card = new VerticalLayout();
        card.setWidth("700px");
        card.setAlignItems(Alignment.CENTER);
        card.setSpacing(true);
        card.setPadding(true);

        card.getStyle()
                .set("background", "white")
                .set("margin-top", "50px")
                .set("padding", "40px")
                .set("border-radius", "20px")
                .set("box-shadow", "0 8px 25px rgba(0,0,0,0.12)");

        VerticalLayout header = getHeader();

        Image img = new Image("images/logo.png", "Vibe Coding logo");
        img.setWidth("220px");
        img.getStyle()
                .set("margin", "20px 0");

        Paragraph description = new Paragraph(
                "Still coding the old way? Join our Vibe Coding courses — where bugs become features, " +
                        "prompts become projects, and 'it works on my machine' becomes a lifestyle. " +
                        "We don’t just teach programming — we teach you to code with vibes."
        );

        description.setWidth("560px");
        description.getStyle()
                .set("font-size", "20px")
                .set("line-height", "1.6")
                .set("text-align", "center")
                .set("color", "#333");

        VerticalLayout address = new VerticalLayout();
        address.setAlignItems(Alignment.CENTER);
        address.setSpacing(false);
        address.getStyle()
                .set("margin-top", "20px")
                .set("color", "#555");

        H3 name = new H3("Vibe Coding GmbH");
        H3 street = new H3("Spengergasse 20");
        H3 city = new H3("1050 Wien");

        name.getStyle().set("margin", "4px");
        street.getStyle().set("margin", "4px");
        city.getStyle().set("margin", "4px");

        address.add(name, street, city);

        card.add(header, img, description, address);
        add(card);
    }

    public static VerticalLayout getHeader() {
        VerticalLayout header = new VerticalLayout();
        header.setAlignItems(Alignment.CENTER);
        header.setSpacing(false);
        header.setPadding(false);

        H1 companyName = new H1("Vibe Coding");
        companyName.getStyle()
                .set("font-family", "cursive")
                .set("font-size", "4rem")
                .set("margin", "0")
                .set("color", "#222");

        H2 subName = new H2("... We’ll teach you how to vibe code ...");
        subName.getStyle()
                .set("margin", "0")
                .set("font-size", "1.4rem")
                .set("color", "#777");

        header.add(companyName, subName);

        return header;
    }
}
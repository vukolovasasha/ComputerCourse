package at.spengergasse.views.price;

import at.spengergasse.views.computercourse.ComputerCourseView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.lineawesome.LineAwesomeIconUrl;

@PageTitle("Price")
@Route("price")
@Menu(order = 2, icon = LineAwesomeIconUrl.DOLLAR_SIGN_SOLID)
public class PriceView extends VerticalLayout {

    public PriceView() {
        setWidthFull();
        setMinHeight("100vh");

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

        VerticalLayout header = ComputerCourseView.getHeader();

        H2 priceList = new H2("Price List");
        priceList.getStyle()
                .set("margin", "20px 0 30px 0")
                .set("color", "#777");

        FlexLayout courses = new FlexLayout();
        courses.setWidthFull();
        courses.setJustifyContentMode(JustifyContentMode.CENTER);
        courses.setFlexWrap(FlexLayout.FlexWrap.WRAP);

        courses.getStyle()
                .set("gap", "24px")
                .set("max-width", "1350px");

        VerticalLayout beginner = getCard(
                "Beginner Viber",
                "HTML & CSS Basics", 149,
                "Java Fundamentals", 199,
                "Git & GitHub", 79
        );

        VerticalLayout explorer = getCard(
                "Code Explorer",
                "Java OOP", 299,
                "SQL & PostgreSQL", 249,
                "Frontend Development", 349
        );

        VerticalLayout survivor = getCard(
                "Bug Survivor",
                "Spring Boot & Vaadin", 499,
                "DevOps Basics", 399,
                "Clean Architecture", 1000
        );

        VerticalLayout cowboy = getCard(
                "AI Cowboy",
                "Prompt Engineering", 199,
                "AI Agents", 399,
                "LLM Integration", 599
        );

        VerticalLayout extra = getCard(
                "Additional Services",
                "Certificate", 29,
                "1:1 Mentoring / hour", 49,
                "Project Review", 69
        );

        courses.add(beginner, explorer, survivor,cowboy, extra);

        VerticalLayout pageCard = new VerticalLayout();
        pageCard.setAlignItems(Alignment.CENTER);
        pageCard.setPadding(true);
        pageCard.setSpacing(false);
        pageCard.setWidth("90%");
        pageCard.setMaxWidth("1450px");

        pageCard.getStyle()
                .set("background", "white")
                .set("margin-top", "50px")
                .set("margin-bottom", "50px")
                .set("padding", "40px")
                .set("border-radius", "20px")
                .set("box-shadow", "0 8px 25px rgba(0,0,0,0.12)");

        pageCard.add(header, priceList, courses);

        add(pageCard);
    }

    public VerticalLayout getCard(String courseName,
                                  String course1, double price1,
                                  String course2, double price2,
                                  String course3, double price3) {

        VerticalLayout card = new VerticalLayout();

        H2 title = new H2(courseName);

        Paragraph p1 = new Paragraph(course1 + " - " + price1 + " €");
        Paragraph p2 = new Paragraph(course2 + " - " + price2 + " €");
        Paragraph p3 = new Paragraph(course3 + " - " + price3 + " €");

        card.setWidth("280px");
        card.setMinHeight("230px");
        card.setPadding(true);
        card.setSpacing(false);
        card.setAlignItems(Alignment.START);

        card.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "16px")
                .set("box-shadow", "0 4px 12px rgba(0,0,0,0.08)")
                .set("background-color", "#ffffff")
                .set("padding", "24px");

        title.getStyle()
                .set("margin-top", "0")
                .set("margin-bottom", "18px")
                .set("color", "#1f2937");

        p1.getStyle().set("margin", "6px 0");
        p2.getStyle().set("margin", "6px 0");
        p3.getStyle().set("margin", "6px 0");

        card.add(title, p1, p2, p3);

        return card;
    }
}
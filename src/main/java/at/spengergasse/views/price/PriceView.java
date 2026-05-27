package at.spengergasse.views.price;

import at.spengergasse.views.computercourse.ComputerCourseView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
        setSpacing(true);
        setPadding(true);
        setAlignItems(Alignment.CENTER);

        VerticalLayout header = ComputerCourseView.getHeader();

        H2 priceList = new H2("Price List");
        priceList.getStyle()
                .set("margin", "0")
                .set("color", "gray");

        FlexLayout courses = new FlexLayout();

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

        courses.setWidthFull();
        courses.setJustifyContentMode(
                JustifyContentMode.CENTER);

        courses.setFlexWrap(
                FlexLayout.FlexWrap.WRAP);

        courses.getStyle()
                .set("gap", "20px");

        courses.add(beginner, explorer, survivor);

        VerticalLayout extra = new VerticalLayout();

        H2 extras = new H2("Additional Services");
        Paragraph cert = new Paragraph("Certificate +29 €");
        Paragraph mentor = new Paragraph("1:1 Mentoring 49 €/hour");

        extra.add(extras, cert, mentor);

        extra.setWidth("300px");
        extra.setPadding(true);
        extra.setSpacing(false);
        extra.setAlignItems(Alignment.START);

        extra.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "12px")
                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.08)")
                .set("background-color", "white");

        extras.getStyle().set("margin-top", "0");

        add(header, priceList, courses, extra);
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


        card.setWidth("300px");
        card.setPadding(true);
        card.setSpacing(false);
        card.setAlignItems(Alignment.START);

        card.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "12px")
                .set("box-shadow", "0 2px 8px rgba(0,0,0,0.08)")
                .set("background-color", "white");

        title.getStyle().set("margin-top", "0");

        card.add(title, p1, p2, p3);

        return card;
    }
}
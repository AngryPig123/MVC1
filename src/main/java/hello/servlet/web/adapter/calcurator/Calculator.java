package hello.servlet.web.adapter.calcurator;

public class Calculator implements CalculatorInterface{

    Rectangle rectangle;

    @Override
    public double getArea(Rectangle rectangle) {
        this.rectangle = rectangle;
        return rectangle.length * rectangle.length;
    }

}

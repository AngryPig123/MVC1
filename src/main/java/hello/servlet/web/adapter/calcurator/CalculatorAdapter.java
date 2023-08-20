package hello.servlet.web.adapter.calcurator;

public class CalculatorAdapter implements CalculatorInterface{

    Calculator calculator;
    Triangle triangle;

    public CalculatorAdapter(Triangle triangle) {
        this.triangle = triangle;
    }

    @Override
    public double getArea(Rectangle rectangle) {
        return 0;
    }

}

package hello.servlet.web.adapter.duckturkey;

public class MallardDuck implements Duck{

    @Override
    public void quack() {
        System.out.println("mallard");
    }

    @Override
    public void fly() {
        System.out.println("i am flying");
    }
}

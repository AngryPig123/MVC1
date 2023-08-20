package hello.servlet.web.adapter.duckturkey;

public class Client {

    public static void main(String[] args) {

        MallardDuck mallardDuck = new MallardDuck();
        WildTurkey wildTurkey = new WildTurkey();

        Duck turkeyAdapter = new TurkeyAdapter(wildTurkey);
        turkeyAdapter.fly();
        turkeyAdapter.quack();

    }

}

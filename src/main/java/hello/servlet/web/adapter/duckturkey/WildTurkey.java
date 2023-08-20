package hello.servlet.web.adapter.duckturkey;

public class WildTurkey implements Turkey{

    @Override
    public void gobble() {
        System.out.println("gobble");
    }

    @Override
    public void fly() {
        System.out.println("i am flying a short distance");
    }

}

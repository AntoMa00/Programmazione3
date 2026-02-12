package it.bripobe;

public class StarDecorator implements Decorator {
    @Override
    public String decorate(String input) {
        return "*** " + input.toUpperCase() + " ***";
    }
}
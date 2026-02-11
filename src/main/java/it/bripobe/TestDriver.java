package it.bripobe;

public class TestDriver {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Driver trovato!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver NON trovato!");
            e.printStackTrace();
        }
    }
}
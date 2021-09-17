package hw03_07;

public class Tester {
    public static void main(String[] args) {
        start(Testee.class);
    }

    public static void start(Class testClass) {
        new TestEngine(testClass).run();
    }
}

package hw03_07;

public class Testee {
    @BeforeSuite
    public void doBefore() {
        System.out.println("Do Before");
    }

//    @BeforeSuite
//    public void doBefore1() {
//        System.out.println("Do Before1");
//    }

    @AfterSuite
    public void doAfter() {
        System.out.println("Do After");
    }
    @Test(priority = 1)
    public void test1() {
        System.out.println("test1");
    }
    @Test(priority = 1)
    public void test1_1() {
        System.out.println("test1_1");
    }
    @Test(priority = 2)
    public void test2() {
        System.out.println("test2");
    }
    @Test(priority = 3)
    public void test3() {
        System.out.println("test3");
    }
    @Test(priority = 10)
    public void test10() {
        System.out.println("test10");
    }
}

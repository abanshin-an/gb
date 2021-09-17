package hw03_07;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
/*
В качестве тестов выступают классы с наборами методов, снабженных аннотациями @Test. Класс, запускающий тесты,
должен иметь статический метод start(Class testClass), которому в качестве аргумента передается объект типа Class.
Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если он присутствует.
Далее запускаются методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
К каждому тесту необходимо добавить приоритеты (int-числа от 1 до 10), в соответствии с которыми будет выбираться
порядок их выполнения. Если приоритет одинаковый, то порядок не имеет значения.
Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре.
Если это не так – необходимо бросить RuntimeException при запуске «тестирования».
 */

public class TestEngine {
    private final Class<Object> testee;
    private final String testeeName;
    private final SortedMap<Integer, List<Method>> testingMethods = new TreeMap<>();
    private Method beforeSuite;
    private Method afterSuite;

    public TestEngine(Class<Object> testee) {
        this.testee = testee;
        this.testeeName = testee.getName();
    }

    public void run() {
        analyzeAnnotation();
        executeTests();
    }

    private void executeTests() {

        try {
            Object testObject = testee.newInstance();
            if (beforeSuite != null) {
                beforeSuite.invoke(testObject);
            }
            testingMethods.forEach((k, l) -> {
                for (Method m : l) {
                    try {
                        m.invoke(testObject);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (afterSuite != null) {
                afterSuite.invoke(testObject);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void analyzeAnnotation() {
        Method[] methods = testee.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation a : annotations) {
                if (a != null) {
                    String name = a.annotationType().getSimpleName();
                    processAnnotation(method, a, name);
                }
            }
        }
    }

    private void processAnnotation(Method method, Annotation a, String name) {
        if (name.equals("BeforeSuite")) {
            if (beforeSuite != null) {
                throw new RuntimeException("@BeforeSuite in class " + testeeName + " duplicated");
            } else beforeSuite = method;
        }
        if (name.equals("AfterSuite")) {
            if (afterSuite != null) {
                throw new RuntimeException("@AfterSuite in class " + testeeName + " duplicated");
            } else afterSuite = method;
        }
        if (name.equals("Test")) {
            Integer p = ((Test)a).priority();
            List<Method> l;
            if (testingMethods.containsKey(p)) {
                l = testingMethods.get(p);
            }
            else{
                l=new ArrayList<>();
                testingMethods.put(p,l);
            }
            l.add(method);
        }
    }
}

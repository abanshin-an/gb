import java.util.ArrayList;
import java.util.List;

/*
Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть
новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
Написать набор тестов для этого метода (по 3-4 варианта входных данных).
Вх: [1 2 4 4 2 3 4 1 7] -> вых: [1 7 ].
Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
[1 1 1 4 4 1 4 4] -> true
[1 1 1 1 1 1] -> false
[4 4 4 4] -> false
[1 4 4 1 1 4 3] -> false
 */
public class MainClass {

    public int[] makeArray(int[] array) throws RuntimeException {
        List<Integer> l = new ArrayList<>();
        boolean addToList=false;
        for (int j : array) {
            if (j == 4) {
                l.clear();
                addToList = true;
            } else if (addToList)
                l.add(j);
        }
        if (!addToList)
            throw new RuntimeException();
        return l.stream().mapToInt(i -> i).toArray();
    }

    public boolean checkArray(int[] array) {
        boolean has1 = false;
        boolean has4 = false;
        for (int j : array) {
            if (j != 1 && j != 4) return false;
            if (j == 1) has1 = true;
            if (j == 4) has4 = true;
        }
        return (has1 && has4);
    }

}

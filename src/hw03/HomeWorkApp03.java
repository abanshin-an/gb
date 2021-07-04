package hw03;

import sun.reflect.generics.tree.ArrayTypeSignature;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class HomeWorkApp03 {
    final static Random random = new Random();

    public static void main(String[] args) {
        int arr[];
        ex1();
        ex2();
        ex3();
        ex4();
        int len=random.nextInt(90)+1;
        int initialValue=random.nextInt(90);
        arr=ex5(len,initialValue);
        System.out.println(Arrays.toString(arr));
        ex6();
        len=random.nextInt(10)+1;
        int[] arr1=new int[len];
        for (int i=0;i<arr1.length;i++)
            arr1[i]=random.nextInt(10);
        ex7(arr1);
        ex7(new int[]{2, 2, 2, 1, 2, 2, 10, 1}); //→ true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
        ex7(new int[]{1, 14, 1,  2, 1, 6, 7}); // → true, т.е. 1 + 14 + 1 = 2 + 1 + 6 + 7
        ex7(new int[]{1, 1, 1, 2, 1});// → true, т.е. 1 + 1 + 1 = 2 + 1
        ex8(new int[]{1, 2, 3, 4, 5, 6, 7, 8},2);
        ex8(new int[]{1, 2, 3, 4, 5, 6, 7, 8},-3);
        ex8(new int[]{1, 2, 3, 4, 5},6);
    }

    private static void ex1() {
        System.out.println("1. Задать целочисленный массив, состоящий из элементов 0 и 1.");
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int[] arr = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void ex2() {
        System.out.println("2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100");
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; )
            arr[i] = ++i;
        System.out.println(Arrays.toString(arr));
    }

    private static void ex3() {
        System.out.println("3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;");
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6)
                arr[i] *= 2;
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void ex4() {
        System.out.println("4. Создать квадратный двумерный целочисленный массив(количество строк и столбцов одинаковое),");
        // и с помощью цикла(-ов) заполнить его диагональные элементы
        // единицами(можно только одну из диагоналей, если обе сложно).Определить элементы одной из диагоналей можно по
        // следующему принципу:индексы таких элементов равны, то есть[0][0], [1][1], [2][2], …, [n][n];
        final int SIZE=10;
        int[][] arr=new int[SIZE][SIZE];
        for (int i=0;i<SIZE;i++) {
            for(int j=0;j<SIZE;j++) {
                int v = ((i == j) || (i == SIZE - j - 1)) ? 1 : 0;
                arr[i][j] = v;
            }
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    private static int[] ex5(int len, int initialValue) {
        System.out.println("5. Написать метод, принимающий на вход два аргумента: len и initialValue,");
        // и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
        System.out.printf("ext(%2d, %2d)\n",len,initialValue);
        int[] arr=new int[len];
        Arrays.fill(arr,initialValue);
        return arr;
    }
    private static void ex6() {
        System.out.println("6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы ;");
        final int SIZE=50;
        int[] arr = new Random().ints(SIZE).toArray();
        int min = Arrays.stream(arr).min().getAsInt();
        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println("arr= "+ Arrays.toString(arr));
        System.out.printf("min = %d; max=%d \n",min,max);
    }
    private static boolean ex7(int[] arr) {
        System.out.println("7. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.");
//**Примеры:
//        checkBalance([2, 2, 2, 1, 2, 2, ||| 10, 1]) → true, т.е. 2 + 2 + 2 + 1 + 2 + 2 = 10 + 1
//        checkBalance([1, 1, 1, ||| 2, 1]) → true, т.е. 1 + 1 + 1 = 2 + 1
//        checkBalance([1, 14, 1, ||| 2, 1, 6, 7]) → true, т.е. 1 + 14 + 1 = 2 + 1 + 6 + 7
//        граница показана символами |||, эти символы в массив не входят и не имеют никакого отношения к ИЛИ.
        int sum=0;
        int lsum=0;
        int i;
        for (i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        for(i=0;i<arr.length;i++){
            lsum+=arr[i];
            if (lsum==sum-lsum) {
                break;
            }
        }
        System.out.println("Split array "+Arrays.toString(arr));
        if (0<i && i<arr.length) {
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, i + 1)) + "||" +
                    Arrays.toString(Arrays.copyOfRange(arr, i + 1, arr.length)) + " sum= "+lsum);
            return true;
        }
        System.out.println("Can't split array");
        return false;
    }

    private static void ex8(int[] arr, int shift) {
        System.out.println("8. *** Сдвинуть массив");
//        8. *** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
//        Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами. Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ]; [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ]. При каком n в какую сторону сдвиг можете выбирать сами.
        System.out.println(Arrays.toString(arr) +" shift = "+ shift);
        int l = arr.length;
        shift = shift % l;
        if ( shift<0 )
            shift = l+shift;
        for(int i = 0; i <shift ; i++){
            for(int j = l - shift - 1; j > -1 ; j--){
                int t=arr[i + j];
                arr[i + j]=arr[i + j + 1];
                arr[i + j + 1]=t;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}

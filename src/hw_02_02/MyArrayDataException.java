package hw_02_02;


import java.util.Arrays;

public class MyArrayDataException extends ArrayStoreException {
    public MyArrayDataException(String[][] a, int row, int col, String value) {
        super(makeMessage(a, row, col, value));
    }

    private static String makeMessage(String[][] a, int row, int col, String value) {
        StringBuilder sBuilder = new StringBuilder("\n[");
        for (String[] a1 : a) {
            sBuilder.append("\n").append(Arrays.toString(a1));
        }
        sBuilder.append("] \nЭлемент масива [").append(row).append("][").append(col)
                .append("] содержит неверное значение ").append(value)
                .append(" которое нельзя преобразовать в int");
        return sBuilder.toString();
    }
}

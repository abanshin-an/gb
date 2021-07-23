package hw_02_02;

import java.util.Arrays;

public class MyArraySizeException extends ArrayIndexOutOfBoundsException {
    public MyArraySizeException(String[][] a, int row, int size) {
        super(makeMessage(a, row, size));
    }

    // возможно это не лучшее решение в многопоточном приложении
    private static String makeMessage(String[][] a, int row, int size) {
        StringBuilder sBuilder = new StringBuilder("\n[");
        for (String[] a1 : a) {
            sBuilder.append("\n").append(Arrays.toString(a1));
        }
        if (row == -1) {
            sBuilder.append("]\nНеверное количество строк ").append(size).append(" вместо 4-х");
        } else {
            sBuilder.append("]\nВ строке ").append(row).append(" находится ").append(size).append(" элементов вместо 4-х");
        }
        return sBuilder.toString();
    }
}

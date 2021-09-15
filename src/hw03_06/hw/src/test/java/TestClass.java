import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {
    private MainClass c;

    static Stream<Arguments> test1DataProvider() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}, null),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1, 4, 1}, new int[]{1},null),
                Arguments.arguments(new int[]{1, 2, 3, 5, 6, 7}, new int[]{}, RuntimeException.class),
                Arguments.arguments(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1, 4}, new int[]{},null)
        );
    }

    static Stream<Arguments> test2DataProvider() {
        /*
        [ 1 1 1 4 4 1 4 4 ] -> true
        [ 1 1 1 1 1 1 ] -> false
        [ 4 4 4 4 ] -> false
        [ 1 4 4 1 1 4 3 ] -> false
         */
        return Stream.of(
                Arguments.arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true),
                Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1}, false),
                Arguments.arguments(new int[]{4, 4, 4, 4}, false),
                Arguments.arguments(new int[]{1, 4, 4, 1, 1, 4, 3}, false)
        );
    }

    @BeforeEach
    public void init() {
        c = new MainClass();
    }

    @ParameterizedTest
    @MethodSource("test1DataProvider")
    <T extends Throwable> void test1(int[] param, int[] expected, Class<T> expectedException ) {
        if (expectedException==null)
        assertArrayEquals(expected, c.makeArray(param));
        else
            assertThrows( expectedException, ()->c.makeArray(param));
    }

    @ParameterizedTest
    @MethodSource("test2DataProvider")
    void test2(int[] param, boolean expected) {
        assertEquals(expected, c.checkArray(param));
    }

}

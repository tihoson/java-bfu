package ru.bfu.ipmit.lab2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GetKSmallestNumbersTests {

    @Test
    void shouldReturnKSmallestNumbers() {
        byte[] array = {8, 9, 1, 10};
        int k = 2;

        byte[] actualResult = Lab2.getKSmallestNumbers(array, k);

        byte[] expectedResult = {1, 8};
        assertArrayEquals(expectedResult, actualResult);

        actualResult = Lab2.getKSmallestNumbers(array, array.length);
        Arrays.sort(array);
        assertArrayEquals(array, actualResult);
    }

}

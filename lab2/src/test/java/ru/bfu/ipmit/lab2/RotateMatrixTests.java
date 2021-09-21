package ru.bfu.ipmit.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateMatrixTests {

    @Test
    void shouldRotateMatrix() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] actualResult = Lab2.rotateMatrix(matrix);

        int[][] expectedResult = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertArrayEquals(expectedResult, actualResult);

        int[][] secondTest = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        actualResult = Lab2.rotateMatrix(secondTest);
        int[][] expectedResultSecondTest = {{13, 9, 5, 1}, {14, 10, 6, 2}, {15, 11, 7, 3}, {16, 12, 8, 4}};
        assertArrayEquals(expectedResultSecondTest, actualResult);
    }

}

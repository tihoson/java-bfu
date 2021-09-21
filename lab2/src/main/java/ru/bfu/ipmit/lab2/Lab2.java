package ru.bfu.ipmit.lab2;

import java.util.Arrays;

public class Lab2 {

    /**
     * Метод getKSmallestNumbers возвращает k наименьших элементов в массиве array, отсортированных в порядке возрастания
     *
     * @param array неотсортированный массив типа byte произвольной длины n
     * @param k целочисленный параметр, 0 <= k <= n
     * @return отсортированный массив из k наименьших элементов
     *
     * ПРИМЕР:
     * Вход: array = [8, 9, 1, 10], k = 2
     * Выход: [1, 8]
     *
     */
    static byte[] getKSmallestNumbers(byte[] array, int k) {
        assert(k >= 0 && k <= array.length);

        byte[] result = new byte[k];
        Arrays.sort(array);
        System.arraycopy(array, 0, result, 0, k);
        return result;
    }

    /**
     * Метод arePermutations проверяет, является ли первая строка перестановкой второй, то есть может ли первая строка
     * быть получена из второй путем перестановки символов.
     *
     * @param firstString первая строка произвольной длины, состоящая из цифр 0...9
     *                    и строчных букв латинского алфавита a...z
     * @param secondString вторая строка произвольной длины
     * @return true, если первая строка является перестановкой второй
     *         false, в противном случае
     *
     * ПРИМЕР 1:
     * Вход: firstString = "ab1", secondString = "b1a"
     * Выход: true
     *
     * ПРИМЕР 2:
     * Вход: firstString = "ab", secondString = "aa"
     * Выход: false
     *
     */
    static boolean arePermutations(String firstString, String secondString) {
        return Arrays.equals(
                firstString.chars().sorted().toArray(),
                secondString.chars().sorted().toArray()
        );
    }

    /**
     * Метод rotateMatrix совершает поворот элементов квадратной матрицы на 90 градусов по часовой стрелке
     *
     * @param matrix целочисленная квадратная матрица
     * @return целочисленная квадратная матрица, полученная путем поворота исходной матрицы на 90 градусов
     *         по часовой стрелке
     *
     * ПРИМЕР:
     * Вход: matrix = [1, 2, 3
     *                 4, 5, 6
     *                 7, 8, 9]
     * Выход: [7, 4, 1
     *         8, 5, 2
     *         9, 6, 3]
     *
     */
    static int[][] rotateMatrix(int[][] matrix) {
        for (int[] ints : matrix)
            assert (ints.length == matrix.length);

        int size = matrix.length;

        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                result[i][j] = matrix[size - 1 - j][i];

        return result;
    }

}

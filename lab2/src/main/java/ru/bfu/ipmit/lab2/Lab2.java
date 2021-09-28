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

        // посчитаем вхождение каждого байта
        int[] count = new int[256];
        for (byte value: array)
            count[value]++;

        byte[] result = new byte[k];

        byte curByte = 0;

        // для каждого байта будем вставлять его до тех пор, пока можем
        for (int i = 0; k > 0;) {
            while (count[curByte] > 0 && k > 0) {
                result[i++] = curByte;
                count[curByte]--;
                k--;
            }
            curByte++;
        }

        // Итоговая сложность: O(n)
        // Затраты по памяти: O(1), т.к. размер массива не зависит от размера входных данных

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
        // т.к. входные данные имеют ограничения, т.е. нам подаются только цифры и строчные буквы
        // то мы можем воспользоваться этим фактом
        // '0' - 48, 'z' - 122
        // все остальные цифры и буквы лежат в интервале между 48 и 122
        //
        // учитывая данные ограничения мы можем посчитать вхождения каждого символа в первую строку
        // затем вычесть количество вхождений каждого символа из второй строки
        // если в результате какой-то элемент массива счетчиков будет отличен от нуля, то массивы не будут перестановками

        // выделим память на всю ASCII таблицу
        int[] count = new int[256];
        firstString.chars().forEach(x -> count[x]++);
        secondString.chars().forEach(x -> count[x]--);

        return Arrays.stream(count).noneMatch(x -> x != 0);
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

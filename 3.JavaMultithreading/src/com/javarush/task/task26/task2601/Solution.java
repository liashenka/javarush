package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
Реализуй логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы.
Верни отсортированный массив от минимального расстояния до максимального.
Если удаленность одинаковая у нескольких чисел, то сортируй их в порядке возрастания.

Пример входящего массива:
13, 8, 15, 5, 17
медиана - 13

Отсортированный масив:
13, 15, 17, 8, 5
*/
public class Solution {

    public static void main(String[] args) {
/*
        Integer[] sortedArray = new Integer[]{13, 8, 15, 5, 17};
        sort(sortedArray);
        for (int i : sortedArray) {
            System.out.println(i);
        }
        */
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        final double mediana;
        int mid = array.length / 2;
        Arrays.sort(array);

        if(array.length % 2 != 0){
            mediana = array[mid];
        } else {
            mediana = (array[mid - 1] + array[mid]) / 2.0;
        }

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double result = Math.abs(o1 - mediana) - Math.abs(o2 - mediana);

                return result == 0 ? o1 - 02 : (int) Math.round(result);
            }
        });

        return array;
    }
}

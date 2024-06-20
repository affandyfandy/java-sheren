// Write a Java program that sorts an array of integers using multiple threads.

import java.util.Arrays;

class MultiThreadArraySort {
    public static void multiThreadSort(int[] array) {
        if (array.length <= 1) {
            return;
        }

        int mid = array.length / 2;

        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        Thread leftSort = new Thread(() -> multiThreadSort(left));
        Thread rightSort = new Thread(() -> multiThreadSort(right));

        leftSort.start();
        rightSort.start();

        try {
            leftSort.join();
            rightSort.join();
        } catch (InterruptedException e) {
        }
        
        merge(array, left, right);
    }
    

    private static void merge(int[] result, int[] left, int[] right) {
        // i index pointer of left array, j for right array, and k for result
        int i = 0, j = 0, k = 0;

        // Compare elements from left and right arrays and place the smaller element into the result array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left array to the result array
        while (i < left.length) {
            result[k++] = left[i++];
        }

        // Copy any remaining elements from the right array to the result array
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
    
    public static void main(String[] args) {
        int[] array = {34, 7, 53, 14, 35, 33, 24, 19};

        System.out.println("Original array: " + Arrays.toString(array));

        multiThreadSort(array);

        System.out.println("Sorted array: " + Arrays.toString(array));
    }
}
import java.util.ArrayList;
import java.util.List;

public class Assignment3 {
    public static List<Integer> getSecondBiggestIndex(int[] array) {
        List<Integer> indices = new ArrayList<>();
        if (array.length < 2) {
            return indices; 
        }

        int biggest = Integer.MIN_VALUE;
        int secondBiggest = Integer.MIN_VALUE;

        for (int num : array) {
            if (num > biggest) {
                secondBiggest = biggest;
                biggest = num;
            } else if (num > secondBiggest && num != biggest) {
                secondBiggest = num;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == secondBiggest) {
                indices.add(i);
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        int[] input = {1, 4, 3, -6, 5, 4};
        List<Integer> result = getSecondBiggestIndex(input);
        System.out.println(result);
    }
}

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment3 {
    public static ArrayList<Integer> getSecondBiggestIndices(ArrayList<Integer> array) {
        ArrayList<Integer> indices = new ArrayList<>();

        int biggest = Integer.MIN_VALUE;
        int secondBiggest = Integer.MIN_VALUE;
        int current = 0;

        for (int i = 0; i < array.size(); i++) {
            int input = array.get(i);
            if (input > biggest) {
                secondBiggest = biggest;
                biggest = input;
                current = array.indexOf(secondBiggest);
                indices.clear();
            }
                    
            if (input == secondBiggest) {
                indices.add(i);
            }
        }

        indices.add(0, current);
        return indices;
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>(Arrays.asList(1, 4, 3, -6, 5, 4));
        ArrayList<Integer> output = getSecondBiggestIndices(array);
        System.out.println(output);  // output: [1, 5]
    }
}

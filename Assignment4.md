import java.util.*;

public class Assignment4 {
    public static int[] findSubarray(int[] nums) {
        Map<Integer, Integer> sumIndexMap = new HashMap<>();
        int sum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            if (sum == 0) {
                return new int[]{0, i};
            }
            
            if (sumIndexMap.containsKey(sum)) {
                return new int[]{sumIndexMap.get(sum) + 1, i};
            }
            
            sumIndexMap.put(sum, i);
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -6, 5, 4, 0};
        int[] result = findSubarray(nums);
        
        if (result != null) {
            System.out.println(result[0] + " to " + result[1]);
        } else {
            System.out.println("No subarray found.");
        }
    }
}

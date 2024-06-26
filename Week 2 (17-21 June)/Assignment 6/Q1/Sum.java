import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sum {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Using parallel stream to sum the numbers
        Integer sum = integers.parallelStream().collect(Collectors.summingInt(Integer::intValue));
        
        System.out.println(sum);
    }
}


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();

        // 1. 오름차순
        input = makeInputReverse(input);

        // 2. 0을 포함하는지 확인 (0을 포함하면 무조건 마지막 수가 0)
        boolean hasZero = input.contains("0");

        // 3. 각 자리 수의 합이 3의 배수인지 확인
        boolean isMultipleOf3 = isMultipleOf3(input);

        if(hasZero && isMultipleOf3) {
            System.out.println(input);
        } else {
            System.out.println(-1);
        }
    }

    static String makeInputReverse(String input) {
        char[] nums = input.toCharArray();
        Arrays.sort(nums);
        return new StringBuilder(new String(nums)).reverse().toString();
    }

    static boolean isMultipleOf3(String input) {
        char[] nums = input.toCharArray();

        int sum = 0;

        for (char num : nums) {
            sum += Character.getNumericValue(num);
        }

        if (sum % 3 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

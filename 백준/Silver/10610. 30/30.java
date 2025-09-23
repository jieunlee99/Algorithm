
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		input = sort(input);
		boolean hasZero = input.contains("0");
		boolean isMultipleOf3 = isMultipleOf3(input);
		if (hasZero && isMultipleOf3) {
			System.out.println(input);
		} else {
			System.out.println(-1);
		}
	}

	static String sort(String input) {
		char[] nums = input.toCharArray();
		Arrays.sort(nums);
		return new StringBuilder(new String(nums)).reverse().toString();
	}

	static boolean isMultipleOf3(String input) {
		char[] nums = input.toCharArray();

		int sum = 0;

		for (char num : nums) {
			sum += (num - '0');
		}

		if (sum % 3 == 0) {
			return true;
		}
		return false;
	}

}

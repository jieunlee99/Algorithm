import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] subtractGroups = br.readLine().split("-");

		int result = sum(subtractGroups[0]);
		for (int i = 1; i < subtractGroups.length; i++) {
			result -= sum(subtractGroups[i]);
		}

		System.out.println(result);
	}

	static int sum(String subtractGroup) {
		int sum = 0;

		String[] nums = subtractGroup.split("\\+");
		for (String num : nums) {
			sum += Integer.parseInt(num);
		}

		return sum;
	}
}

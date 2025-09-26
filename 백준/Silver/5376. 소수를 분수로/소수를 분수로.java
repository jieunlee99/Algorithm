import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			sb.append(convertToFraction(br.readLine())).append("\n");
		}

		System.out.println(sb);
	}
    
    // 순환소수와 유한소수를 구분하여 변환
	static String convertToFraction(String decimal) {
		if (decimal.contains("(")) {
			return convertRepeatingDecimal(decimal);
		}
		return convertNonRepeatingDecimal(decimal);
	}

	// 순환소수 변환
	static String convertRepeatingDecimal(String decimal) {
		int openParenIndex = decimal.indexOf("(");

		// ex) 0.1(6) -> 16
		String fullNumStr = decimal.substring(2, openParenIndex)
				+ decimal.substring(openParenIndex + 1, decimal.length() - 1);
		long fullNum = Long.parseLong(fullNumStr);

		// 순환되지 않는 부분
		String nonRepeatingStr = decimal.substring(2, openParenIndex);
		long nonRepeatingNum = nonRepeatingStr.isEmpty() ? 0 : Long.parseLong(nonRepeatingStr);

		// 분자
		long numerator = fullNum - nonRepeatingNum;

		// 분모
		int repeatingLength = fullNumStr.length() - nonRepeatingStr.length();
		StringBuilder denominatorBuilder = new StringBuilder();
		// - 순환되는 길이만큼 9를 붙임
		for (int i = 0; i < repeatingLength; i++) {
			denominatorBuilder.append('9');
		}
		// - 순환하지 않는 부분의 길이만큼 0을 붙임
		for (int i = 0; i < nonRepeatingStr.length(); i++) {
			denominatorBuilder.append('0');
		}
		long denominator = Long.parseLong(denominatorBuilder.toString());

		return reduce(numerator, denominator);
	}

	// 유한소수 변환
	static String convertNonRepeatingDecimal(String decimal) {
		String numStr = decimal.substring(2); // "0."을 제거한 문자열

		long numerator = Long.parseLong(numStr); // 분자
		long denominator = (long) Math.pow(10, numStr.length()); // 분모

		return reduce(numerator, denominator);
	}

	// 분자와 분모의 최대공약수로 약분 - 기약분수
	static String reduce(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		return (numerator / gcd) + "/" + (denominator / gcd);
	}

    // 최대공약수 계산
	static long gcd(long a, long b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
}

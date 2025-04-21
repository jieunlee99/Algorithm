import java.io.*;
import java.util.*;

public class Main {

	static char[] paper;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			paper = br.readLine().toCharArray();
			int len = paper.length;
			
			if(isPossible(0, len-1)) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.print(sb);
	}

	static boolean isPossible(int start, int end) {
		// 끝까지 탐색 가능하다면 가능
		if (start == end) {
			return true;
		}

		int mid = (start + end) / 2;

		for (int i = start; i < mid; i++) {
			if (paper[i] == paper[end - i]) {
				return false;
			}

		}

		return isPossible(start, mid - 1) && isPossible(mid + 1, end);
	}
}
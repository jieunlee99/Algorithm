
import java.io.*;
import java.util.*;

public class Main {
	private static int N, M;
	private static int answer = 0;
	private static String[] arr;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new String[N];
		HashSet<String> hashset = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken();
			hashset.add(arr[i]);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			answer = func(hashset, st.nextToken().split("[,]"));
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static int func(HashSet<String> hashset, String[] keyword) {
		for (int i = 0; i < keyword.length; i++) {
			hashset.remove(keyword[i]);
		}
		return hashset.size();
	}

}
import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[] cards;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		HashMap<Integer, Integer> map = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int card = Integer.parseInt(st.nextToken());
			map.put(card, map.getOrDefault(card, 0)+1);
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int card = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(card, 0)).append(" ");
		}
		
		System.out.println(sb);
	}

}

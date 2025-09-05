import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 이름, 횟수
		Map<String, Integer> map = new HashMap<>();

		int treeCnt = 0;

		String treeName;
		while (true) {
			treeName = br.readLine();
			if (treeName == null || treeName.isEmpty()) {
				break;
			}
			map.put(treeName, map.getOrDefault(treeName, 0) + 1);
			treeCnt++;
		}

		List<String> treeList = new ArrayList<>(map.keySet());
		Collections.sort(treeList);

		StringBuilder sb = new StringBuilder();
		for (String tree : treeList) {
			float percent = (map.get(tree) * 100.0f) / treeCnt;
			sb.append(tree).append(" ").append(String.format("%.4f", percent)).append("\n");
		}

		System.out.print(sb);
	}
}

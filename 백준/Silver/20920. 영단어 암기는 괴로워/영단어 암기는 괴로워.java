import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Map<String, Integer> map = new HashMap<>();

		while (N-- > 0) {
			String word = br.readLine();
			if (word.length() >= M) {
				map.put(word, map.getOrDefault(word, 0) + 1);
			}
		}

		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
		entryList.sort(new Comparator<Map.Entry<String, Integer>>() {

			// 우선순위
			// 1. 자주 나오는 단어
			// 2. 길이가 긴 단어
			// 3. 사전순
			
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getValue().equals(o2.getValue())) {
					if (o1.getKey().length() == o2.getKey().length()) {
						return o1.getKey().compareTo(o2.getKey());
					} else {
						return o2.getKey().length() - o1.getKey().length();
					}
				} else {
					return o2.getValue() - o1.getValue();
				}
			}

		});

		StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry:entryList) {
            sb.append(entry.getKey()).append("\n");
        }
        System.out.print(sb);
	}
}

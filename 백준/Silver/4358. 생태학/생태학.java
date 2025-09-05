
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> map = new HashMap<>();

        String tree;
        int treeCnt = 0;

        while (true) {
            tree = br.readLine();
            if (tree == null || tree.isEmpty()) {
                break;
            }
            map.put(tree, map.getOrDefault(tree, 0) + 1);
            treeCnt++;
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);

        StringBuilder sb = new StringBuilder();
        for(String key:keySet) {
            float percent = (map.get(key) * 100.0f) / treeCnt;
            sb.append(key).append(" ")
                    .append(String.format("%.4f", percent)).append("\n");
        }

        System.out.print(sb);
    }
}

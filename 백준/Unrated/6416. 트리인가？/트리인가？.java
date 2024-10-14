
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<Integer, Integer> map;
        Set<Integer> vertex;

        out :
        for(int i=1;;i++) {
            map = new HashMap<>();
            vertex = new HashSet<>();
            boolean flag = false;

            st = new StringTokenizer(br.readLine());
            while(true) {
                if(!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(a == 0) break;
                if(a== -1) break out;

                if(!vertex.add(b)) { // 간선 2개이상 fail
                    flag = true;
                }
                map.put(a, map.getOrDefault(a, 0)+1);
            }

            if(!vertex.isEmpty()) {
                int rootNum=0;
                for(int num : map.keySet()) {
                    if(!vertex.contains(num)) rootNum++;
                }
                if(rootNum!=1) flag = true;
            }
            if(flag) {
                sb.append("Case ").append(i).append(" is not a tree.\n");
            }else {
                sb.append("Case ").append(i).append(" is a tree.\n");
            }
        }
        System.out.println(sb);
    }
}
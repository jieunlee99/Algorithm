
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] result = br.readLine().split("");
        for(int i=0; i<N-1; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j< result.length; j++) {
                if(result[j].equals("?")) {
                    continue;
                }
                else if(!input[j].equals(result[j])) {
                    result[j] = "?";
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< result.length; i++) {
            sb.append(result[i]);
        }

        System.out.println(sb);
    }
}


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String[] input = br.readLine().split("");

            if(input[0].equals("0") && input.length == 1) {
                break;
            }

            if(isPalindrome(input)) {
                sb.append("yes").append("\n");
            } else {
                sb.append("no").append("\n");
            }
        }

        System.out.print(sb);
    }

    static boolean isPalindrome(String[] input) {

        for(int i=0; i<input.length/2; i++) {
            if(!input[i].equals(input[input.length-1-i])){
                return false;
            }
        }

        return true;
    }
}

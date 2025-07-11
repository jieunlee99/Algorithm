import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());

            int scoreP1 = 0;
            int scoreP2 = 0;
            String s = "";

            for(int j=0; j<n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String p1 = st.nextToken();
                String p2 = st.nextToken();


                if((p1.equals("R")&&p2.equals("R"))
                        || (p1.equals("P")&&p2.equals("P"))
                        || (p1.equals("S")&&p2.equals("S"))){
                    scoreP1+=0;
                    scoreP2+=0;
                }
                else if((p1.equals("R")&&p2.equals("S"))
                        || (p1.equals("S")&&p2.equals("P"))
                        || (p1.equals("P")&&p2.equals("R"))){
                    scoreP1+=1;
                }
                else 
                    scoreP2+=1;


                if (scoreP1 > scoreP2) s="Player 1";
                else if (scoreP1 < scoreP2) s="Player 2";
                else s="TIE";
            }
            System.out.println(s);
        }
    }
}

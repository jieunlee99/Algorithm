import java.util.Scanner;
 
public class Main {
    public static void main(String args[]) {
        
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int carlos = sc.nextInt();
        
        String isElected = "S";
        for (int i = 0; i < n - 1; i++) {
            int theother = sc.nextInt();
            if (theother > carlos) isElected = "N";
        }
        
        System.out.print(isElected);
 
    }
}
 
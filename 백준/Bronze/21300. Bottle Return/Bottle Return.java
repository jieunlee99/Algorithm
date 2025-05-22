import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int beer = sc.nextInt();
        int malt = sc.nextInt();
        int wine = sc.nextInt();
        int soft = sc.nextInt();
        int seltzer = sc.nextInt();
        int water = sc.nextInt();
        
        int sum = beer + malt + wine + soft + seltzer + water;
        
        System.out.println(sum * 5);
    }
}
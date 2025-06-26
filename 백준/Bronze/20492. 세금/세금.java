import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int num = sc.nextInt();
        int answer1 = (int)(num * 0.78);
        int answer2 = (int)(num - ((num * 0.2) * 0.22));

        System.out.print(answer1 + " ");
        System.out.println(answer2);
    }
}
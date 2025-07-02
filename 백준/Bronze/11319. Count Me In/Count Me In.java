import java.util.Scanner;
 
public class Main {
    
    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
 
        int n = sc.nextInt();
 
        for (int i = 0; i <= n; i++) {
            String input = sc.nextLine().toLowerCase().trim().replaceAll(" ","");
 
            int vowels = 0;
            int consonants = 0;
            for(int j = 0; j < input.length(); j++) {
                if(input.charAt(j)=='a'||input.charAt(j)=='e'||input.charAt(j)=='i'||input.charAt(j)=='o'||input.charAt(j)=='u') {
                    vowels++;
                } else{
                    consonants++;
                }
            }
            if (vowels != 0 && consonants !=0){
                System.out.println(consonants+" "+vowels);
            }
        }
    }
}
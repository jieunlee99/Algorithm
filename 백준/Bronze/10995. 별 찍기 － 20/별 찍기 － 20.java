import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
      Scanner s=new Scanner(System.in);
      int n=s.nextInt();
      int f=0;
      if(n==1) System.out.print("*");
      else{
      for(int i=1;i<=n;i++){
          for(int j=1;j<=n;j++){
              if(i%2==1){
                  if(f==0){
                      System.out.print("*");
                      f++;
                      j++;
                  }
                  System.out.print(" *");
              }
              else{
                  System.out.print(" *");
              }
              
          }
          f=0;
          System.out.println();
      }
    }
    }
}
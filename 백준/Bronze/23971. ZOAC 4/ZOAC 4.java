import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		double H = Double.parseDouble(st.nextToken());
		double W = Double.parseDouble(st.nextToken());
		double N = Double.parseDouble(st.nextToken());
		double M = Double.parseDouble(st.nextToken());
		
		double answer = Math.ceil(H/(N+1))*Math.ceil(W/(M+1));
		
		System.out.println((int)answer);
		
	}
}


import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		// (R1+R2)/2 = S
		// R1+R2 = 2*S
		// R2 = 2*S - R1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R1 = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		System.out.println(2*S-R1);
	}

}

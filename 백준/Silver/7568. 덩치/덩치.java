import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static Person[] people; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		people = new Person[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			people[i] = new Person(x, y);
		}
	
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(solution(people[i])).append(" ");
		}
		System.out.print(sb.toString());
	}
	
	static int solution(Person p) {
		int cnt = 1;
		
		for(int i=0; i<N; i++)  {
			if(people[i].weight > p.weight && people[i].height > p.height) {
				cnt++;
			}
		}
		
		return cnt;
	}
}

class Person{
	int height, weight;
	
	public Person(int height, int weight) {
		this.height = height;
		this.weight = weight;
	}
}
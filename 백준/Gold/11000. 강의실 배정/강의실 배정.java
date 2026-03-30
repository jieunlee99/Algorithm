import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Lecture> lectures = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(start, end));
        }

        lectures.sort(Comparator.comparingInt(l -> l.start));

        Queue<Lecture> pq = new PriorityQueue<>();
        pq.offer(lectures.get(0));

        for (int i = 1; i < N; i++) {
            Lecture current = pq.peek();

            if (current.end <= lectures.get(i).start) {
                pq.poll();
                current.end = lectures.get(i).end;
                pq.offer(current);
            } else {
                pq.offer(lectures.get(i));
            }
        }

        System.out.println(pq.size());
    }
}

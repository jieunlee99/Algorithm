import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int id, start, end;

        public Lecture(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Lecture{" +
                    "id=" + id +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
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
            int id = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(id, start, end));
        }

        lectures.sort(Comparator.comparingInt(l -> l.start)); // 강의 시작 순 정렬

        PriorityQueue<Lecture> pq = new PriorityQueue<>(); // 강의 종료 오름차순 정렬
        pq.offer(lectures.get(0));

        for (int i = 1; i < N; i++) {
            Lecture current = pq.peek(); // 가장 먼저 끝나는 강의

            // 새 강의실을 빌릴 필요가 없음
            if (current.end <= lectures.get(i).start) {
                pq.poll();
                current.end = lectures.get(i).end;
                pq.offer(current);
            } 

            // 새 강의실을 빌려야 함
            else {
                pq.offer(lectures.get(i));
            }
        }

        System.out.println(pq.size());
    }
}

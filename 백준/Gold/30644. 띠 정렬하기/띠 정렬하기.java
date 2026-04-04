

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Num implements Comparable<Num> {
        int index, value;

        public Num(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Num n) {
            if (this.value == n.value) {
                return this.index - n.index;
            }
            return this.value - n.value;
        }

        @Override
        public String toString() {
            return "Num{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Num[] nums = new Num[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = new Num(i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nums);

        int cnt = 0;
        for (int i = 1; i < N; i++) {
            if (nums[i].index + 1 == nums[i - 1].index
                    || nums[i].index - 1 == nums[i - 1].index) {
                continue;
            }

            cnt++;
        }

        // System.out.println(Arrays.toString(nums));
        System.out.println(cnt);

    }
}

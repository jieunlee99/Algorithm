class Solution {
    public int[] solution(long begin, long end) {
        int first = (int) begin;
        int last = (int) end;

        int[] answer = new int[last - first + 1];

        for (int current = first; current <= last; current++) {

            if (current == 1) {
                answer[current - first] = 0;
                continue;
            }

            int value = 1;

            for (int div = 2; div <= Math.sqrt(current); div++) {

                if (current % div == 0) {

                    int pair = current / div;

                    if (pair <= 10000000) {
                        value = pair;
                        break;
                    }

                    if (div <= 10000000) {
                        value = div;
                    }
                }
            }

            answer[current - first] = value;
        }

        return answer;
    }
}
import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<long[]> points = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        int n = line.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long[] point = findIntersection(line[i], line[j]);

                if (point != null) {
                    long x = point[0];
                    long y = point[1];

                    points.add(new long[]{x, y});

                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);

        char[][] board = new char[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.');
        }

        for (long[] point : points) {
            long x = point[0];
            long y = point[1];

            int row = (int) (maxY - y);
            int col = (int) (x - minX);

            board[row][col] = '*';
        }

        String[] answer = new String[height];

        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }

        return answer;
    }

    private long[] findIntersection(int[] l1, int[] l2) {
        long A = l1[0];
        long B = l1[1];
        long E = l1[2];

        long C = l2[0];
        long D = l2[1];
        long F = l2[2];

        long denominator = A * D - B * C;

        if (denominator == 0) {
            return null;
        }

        long xNum = B * F - E * D;
        long yNum = E * C - A * F;

        if (xNum % denominator != 0 || yNum % denominator != 0) {
            return null;
        }

        long x = xNum / denominator;
        long y = yNum / denominator;

        return new long[]{x, y};
    }
}
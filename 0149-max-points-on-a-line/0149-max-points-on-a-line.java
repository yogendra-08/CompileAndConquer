import java.util.*;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int answer = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int duplicates = 1;
            int best = 0;

            for (int j = i + 1; j < n; j++) {
                long dx = (long) points[j][0] - points[i][0];
                long dy = (long) points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                long gcd = gcd(Math.abs(dx), Math.abs(dy));
                dx /= gcd;
                dy /= gcd;

                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                }

                String slope = dx + "/" + dy;
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                best = Math.max(best, map.get(slope));
            }

            answer = Math.max(answer, best + duplicates);
        }

        return answer;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
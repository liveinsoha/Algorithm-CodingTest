package page547;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ17387 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long x1, y1, x2, y2;
    static long x3, y3, x4, y4;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        x1 = Long.parseLong(st.nextToken());
        y1 = Long.parseLong(st.nextToken());
        x2 = Long.parseLong(st.nextToken());
        y2 = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x3 = Long.parseLong(st.nextToken());
        y3 = Long.parseLong(st.nextToken());
        x4 = Long.parseLong(st.nextToken());
        y4 = Long.parseLong(st.nextToken());

    }

    static void solve() {
        if (isCrossed()) {
            sb.append(1);
        } else {
            sb.append(0);
        }
    }

    static boolean isCrossed() {
        if (hasSamePoint()) {
            return true;
        }
        if (getCCW(x2 - x1, y2 - y1, x3 - x1, y3 - y1) *//한 직선 위인 경우
                getCCW(x2 - x1, y2 - y1, x4 - x1, y4 - y1) == 0 &&
                getCCW(x4 - x3, y4 - y3, x1 - x3, y1 - y3) *
                        getCCW(x4 - x3, y4 - y3, x2 - x3, y2 - y3) == 0) {
            if ((Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2) //오버랩
                    && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2))) {
                return true;
            }
        } else {
            return getCCW(x2 - x1, y2 - y1, x3 - x1, y3 - y1) //이쪽 저쪽
                    * getCCW(x2 - x1, y2 - y1, x4 - x1, y4 - y1) <= 0
                    &&
                    getCCW(x4 - x3, y4 - y3, x1 - x3, y1 - y3) // 이쪽 저쪽 이거나 선분 위
                            * getCCW(x4 - x3, y4 - y3, x2 - x3, y2 - y3) <= 0;
        }
        return false;
    }

    static long getCCW(long a1, long b1, long a2, long b2) {
        if (a1 * b2 - b1 * a2 > 0) {
            return 1;
        } else if (a1 * b2 - b1 * a2 == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    static boolean hasSamePoint() {
        return (x1 == x3 && y1 == y3) || (x1 == x4 && y1 == y4) || (x2 == x3 && y2 == y3) || (x2 == x4 && y2 == y4);
    }
}

package page551;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2162 { //그냥 유니온 파인드
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Line[] lines;
    static int[] unf;
    static int n;

    static int[] fCount;
    static int setCount;
    static int maxSetSize;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        unf = new int[n + 1];
        fCount = new int[n + 1];

        lines = new Line[n + 1];
        for (int i = 1; i <= n; i++) {
            unf[i] = i;
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        maxSetSize = 0;
        setCount = 0;

    }

    static void solve() {
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (isCrossed(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            fCount[find(i)]++;
        }
        for (int i = 1; i <= n; i++) {
            if (fCount[i] != 0) {
                setCount++;
                maxSetSize = Math.max(maxSetSize, fCount[i]);
            }
        }
        sb.append(setCount).append("\n");
        sb.append(maxSetSize);
    }

    static boolean isCrossed(Line line1, Line line2) {
        if (getCCW(line1.x2 - line1.x1, line1.y2 - line1.y1, line2.x1 - line1.x1, line2.y1 - line1.y1) * //평행
                getCCW(line1.x2 - line1.x1, line1.y2 - line1.y1, line2.x2 - line1.x1, line2.y2 - line1.y1) == 0 &&
                getCCW(line2.x2 - line2.x1, line2.y2 - line2.y1, line1.x1 - line2.x1, line1.y1 - line2.y1) *
                        getCCW(line2.x2 - line2.x1, line2.y2 - line2.y1, line1.x2 - line2.x1, line1.y2 - line2.y1) == 0) {
            if (isOverLap(line1, line2)) {
                return true;
            }
        } else {
            if (getCCW(line1.x2 - line1.x1, line1.y2 - line1.y1, line2.x1 - line1.x1, line2.y1 - line1.y1) * //교차
                    getCCW(line1.x2 - line1.x1, line1.y2 - line1.y1, line2.x2 - line1.x1, line2.y2 - line1.y1) <= 0 &&
                    getCCW(line2.x2 - line2.x1, line2.y2 - line2.y1, line1.x1 - line2.x1, line1.y1 - line2.y1) *
                            getCCW(line2.x2 - line2.x1, line2.y2 - line2.y1, line1.x2 - line2.x1, line1.y2 - line2.y1) <= 0) {
                return true;
            }
        }
        return false;
    }

    static boolean isOverLap(Line line1, Line line2) {
        if (Math.min(line1.x1, line1.x2) <= Math.max(line2.x1, line2.x2) && Math.max(line1.x1, line1.x2) >= Math.min(line2.x1, line2.x2) &&
                Math.min(line1.y1, line1.y2) <= Math.max(line2.y1, line2.y2) && Math.max(line1.y1, line1.y2) >= Math.min(line2.y1, line2.y2)) {
            return true;
        }
        return false;
    }

    static long getCCW(long a1, long b1, long a2, long b2) {
        if ((a1 * b2 - b1 * a2) > 0) {
            return 1;
        } else if ((a1 * b2 - b1 * a2) == 0) {
            return 0;
        } else
            return -1;
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    static int find(int x) {
        if (unf[x] == x) {
            return unf[x];
        }
        return unf[x] = find(unf[x]);
    }

    static void union2(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] += unf[fb];
            unf[fb] = fa;
        }
    }

    static int find2(int x) {
        if (unf[x] < 0) {
            return x;
        }
        return unf[x] = find2(unf[x]);
    }

    static class Line {
        long x1;
        long y1;
        long x2;
        long y2;

        Line(long x1, long y1, long x2, long y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}

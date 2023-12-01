package page545;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11758 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long x1;
    static long y1;
    static long x2;
    static long y2;

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
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        x1 = c - a;
        y1 = d - b;
        x2 = e - c;
        y2 = f - d;
    }

    static void solve() {
        if (getCCW() > 0) {
            sb.append(1);
        } else if (getCCW() == 0) {
            sb.append(0);
        } else {
            sb.append(-1);
        }
    }

    static long getCCW() {
        return x1 * y2 - x2 * y1;
    }
}

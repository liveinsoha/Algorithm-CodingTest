package page217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean[] notPn;
    static int n;
    static int m;

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
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        notPn = new boolean[n + 1];
    }

    static void solve() {
        checkNotPn();
        if (m <= 2) {
            sb.append(2).append("\n");
        }
        if (m % 2 == 0) {
            for (int i = m + 1; i <= n; i = i + 2) {
                if (!notPn[i]) {
                    sb.append(i).append("\n");
                }
            }
        } else {
            for (int i = m; i <= n; i = i + 2) {
                if (!notPn[i]) {
                    sb.append(i).append("\n");
                }
            }
        }
    }

    static void checkNotPn() {
        notPn[1] = true;
        for (int i = 3; i <= (int) Math.sqrt(n); i = i + 2) {
            if (!notPn[i]) {
                int times = i + i;
                while (times <= n) {
                    notPn[times] = true;
                    times += i;
                }
            }
        }
    }
}

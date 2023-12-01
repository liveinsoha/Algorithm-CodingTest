package page483완전순열교란순열점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1947 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] disarrange;
    static int n;

    private static final long that = 1000000000L;

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
        initDisarrange();
    }

    static void solve() {
        sb.append(disarrange[n]);
    }

    static void initDisarrange() {
        disarrange = new long[n + 1];
        if (n == 1) {
            return;
        }
        disarrange[2] = 1;
        for (int i = 3; i <= n; i++) {
            disarrange[i] = ((i - 1) * ((disarrange[i - 1] + disarrange[i - 2]) % that)) % that;
        }
    }
}

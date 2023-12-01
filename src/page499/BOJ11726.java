package page499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] countArr;
    static int n;

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
        countArr = new long[n + 1];
        initCountArr();
    }

    static void solve() {
        sb.append(countArr[n]);
    }

    static void initCountArr() {
        if (n == 1) {
            countArr[1] = 1;
            return;
        } else if (n == 2) {
            countArr[1] = 1;
            countArr[2] = 2;
            return;
        }
        countArr[1] = 1;
        countArr[2] = 2;
        for (int i = 3; i <= n; i++) {
            countArr[i] = ((countArr[i - 1] % 10007) + (countArr[i - 2] % 10007)) % 10007;
        }
    }
}

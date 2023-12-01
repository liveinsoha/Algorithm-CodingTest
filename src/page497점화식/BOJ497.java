package page497점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ497 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static long[] end0;
    static long[] end1;

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
        end0 = new long[n + 1];
        end1 = new long[n + 1];
    }

    static void solve() {
        initPinary();
        sb.append(end0[n] + end1[n]);
    }

    static void initPinary() {
        end0[1] = 0;
        end1[1] = 1;
        for (int i = 2; i <= n; i++) {
            end0[i] = end0[i - 1] + end1[i - 1];
            end1[i] = end0[i - 1];
        }

    }
}

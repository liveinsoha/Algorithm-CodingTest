package page489DP점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463_2 {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] minCount;
    static int n;

    public static void main(String[] args) throws IOException{
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException{
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        minCount = new int[n + 1];
        initMinCount();
    }

    static void solve() {
        sb.append(minCount[n]);
    }

    static void initMinCount() {
        for (int i = 2; i <= n; i++) {
            int min = minCount[i - 1] + 1;
            if (i % 3 == 0) {
                min = Math.min(min, minCount[i / 3] + 1);
            }
            if (i % 2 == 0) {
                min = Math.min(min, minCount[i / 2] + 1);
            }
            minCount[i] = min;
        }
    }
}

package page489DP점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1463 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] minCount;
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
        minCount = new int[n + 1];
    }

    static void solve() {
        sb.append(getMin(n));
    }

    static int getMin(int i) {
        if (i == 1) {
            return 0;
        }
        if (minCount[i] != 0) {
            return minCount[i];
        }
        int min;
        if (i % 6 == 0) {
            min = Math.min(getMin(i / 2) + 1, getMin(i / 3) + 1);
        } else if (i % 3 == 0) {
            min = Math.min(getMin(n - 1) + 1, getMin(i / 3) + 1);
        } else if (i % 2 == 0) {
            min = Math.min(getMin(n - 1) + 1, getMin(i / 2) + 1);
        } else {
            min = getMin(i - 1) + 1;
        }
        return minCount[i] = min;
    }
}

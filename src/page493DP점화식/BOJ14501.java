package page493DP점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] maxProfitBefore;
    static int[] time;
    static int[] profit;
    static int n;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(getMaxProfitBefore(n + 1));
    }

    static void solution() throws IOException {

    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        maxProfitBefore = new int[n + 2];
        profit = new int[n + 2];
        time = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
            maxProfitBefore[i] = -1;
        }
        maxProfitBefore[n + 1] = -1;

    }

    static void solve() {

    }

    static int getMaxProfitBefore(int i) {
        if (maxProfitBefore[i] != -1) {
            return maxProfitBefore[i];
        }
        int max = 0;
        for (int j = i - 1; j >= 1; j--) {
            if (can(i, j)) {
                max = Math.max(max, getMaxProfitBefore(j) +profit[j]);
            }
        }
        return maxProfitBefore[i] = max;
    }

    static boolean can(int i, int j) {
        if (j + time[j] <= i) {
            return true;
        }
        return false;
    }
}

package page493DP점화식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501_2 { //그 날부터 끝날 떄 까지의 얻을 수 있는 최대 이익을 D[i]로 정의. 이것을 재활용한다.
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] maxProfitUntilEnd;
    static int[] time;
    static int[] profit;
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
        time = new int[n + 1];
        profit = new int[n + 1];
        maxProfitUntilEnd = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        initMaxProfitUntilEnd();
        sb.append(maxProfitUntilEnd[1]);
    }

    static void initMaxProfitUntilEnd() {
        for (int i = n; i >= 1; i--) {
            if (can(i)) {
                maxProfitUntilEnd[i] = Math.max(maxProfitUntilEnd[i + 1], maxProfitUntilEnd[i + time[i]] + profit[i]);
            } else {
                maxProfitUntilEnd[i] = maxProfitUntilEnd[i + 1];
            }
        }
    }

    static boolean can(int i) {
        return i + time[i] <= n + 1;
    }
}

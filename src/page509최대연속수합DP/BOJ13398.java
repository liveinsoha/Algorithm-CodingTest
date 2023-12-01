package page509최대연속수합DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13398 { //점화식 정의
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[] maxSequentialSumFromLeft;
    static long[] maxSequentialSumFromRight;
    static int[] fromWhereL;
    static int[] fromWhereR;
    static int[] num;
    static int n;
    static long answer;

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
        maxSequentialSumFromLeft = new long[n + 2];
        maxSequentialSumFromRight = new long[n + 2];
        num = new int[n + 1];
        fromWhereL = new int[n + 2];
        fromWhereR = new int[n + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        initMaxSequentialSum();
    }

    static void solve() {
        sb.append(answer);
    }

    static void initMaxSequentialSum() {
        answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (maxSequentialSumFromLeft[i - 1] + num[i] > num[i]) {
                maxSequentialSumFromLeft[i] = maxSequentialSumFromLeft[i - 1] + num[i];
                answer = Math.max(answer, maxSequentialSumFromLeft[i]);
            } else {
                maxSequentialSumFromLeft[i] = num[i];
                answer = Math.max(answer, maxSequentialSumFromLeft[i]);
            }
        }

        for (int i = n; i >= 1; i--) {
            if (maxSequentialSumFromRight[i + 1] + num[i] > num[i]) {
                maxSequentialSumFromRight[i] = maxSequentialSumFromRight[i + 1] + num[i];
            } else {
                maxSequentialSumFromRight[i] = num[i];

            }
        }
        if (n != 1) {
            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, maxSequentialSumFromLeft[i - 1] + maxSequentialSumFromRight[i + 1]);
            }
        }
    }
}

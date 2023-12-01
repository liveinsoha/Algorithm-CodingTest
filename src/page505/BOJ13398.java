package page505;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ13398 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] num;
    static long[] accum;
    static long accumMaxR;
    static long accumMaxL;
    static int maxIdx;
    static int n;
    static int accumMaxRIdx;
    static int accumMaxLIdx;
    static long answer;
    static int maxValue;
    static int minValue;
    static int minIdx;

    public static void main(String[] args) throws IOException { //틀림 구간 최대 순열
        solution();
        System.out.println(answer);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        accum = new long[n + 1];

        maxValue = Integer.MIN_VALUE;
        maxIdx = 0;
        minValue = Integer.MAX_VALUE;
        minIdx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            if (num[i] > maxValue) {
                maxValue = num[i];
                maxIdx = i;
            }
            if (num[i] < minValue) {
                minValue = num[i];
                minIdx = i;
            }
        }
    }

    static void solve() {
        getAccumMax();
        checkValid();
    }

    static void checkValid() {
        answer = maxValue;
        /*if (minValue < 0) {
            if (minIdx < maxIdx) {
                accumMaxL -= minValue;
            } else {
                accumMaxR -= minValue;
            }
        }*/
        if (accumMaxL > 0) {
            answer += accumMaxL;
        }
        if (accumMaxR > 0) {
            answer += accumMaxR;
        }

    }

    static void getAccumMax() {
        if (minValue < 0) {
            num[minIdx] = 0;
        }

        for (int i = maxIdx + 1; i <= n; i++) {
            if ((accum[i] = accum[i - 1] + num[i]) > accumMaxR) {
                accumMaxR = accum[i];
                accumMaxRIdx = i;
            }
        }
        for (int i = maxIdx - 1; i >= 1; i--) {
            if ((accum[i] = accum[i + 1] + num[i]) > accumMaxL) {
                accumMaxL = accum[i];
                accumMaxLIdx = i;
            }
        }
    }
}

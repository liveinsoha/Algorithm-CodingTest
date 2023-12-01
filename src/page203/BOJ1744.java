package page203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1744 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] arr;
    static int sum;

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
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
    }

    static void solve() {
        forNegative();
        forPositive();
        sb.append(sum);
    }

    static void forNegative() {
        int i = 0;
        while (i + 1 < arr.length && arr[i] < 0 && arr[i + 1] < 0) {
            sum += arr[i] * arr[i + 1];
            i = i + 2;
        }
        if (i < arr.length && arr[i] < 0) {
            if (i + 1 < arr.length && arr[i + 1] == 0) {
                sum += 0;
            } else {
                sum += arr[i];
            }
        }

    }

    static void forPositive() {
        int i = n - 1;
        while (i - 1 >= 0 && arr[i] > 1 && arr[i - 1] > 1) {
            sum += arr[i] * arr[i - 1];
            i = i - 2;
        }
        while (i >= 0 && arr[i] > 0) {
            sum += arr[i];
            i--;
        }
    }
}

package page488피보나치동적메모이제이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2747 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] fibo;
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
        if (n >= 3) {
            fibo = new int[n + 1];
            fibo[2] = 1;
            fibo[3] = 2;
        }
    }

    static void solve() {
        if (n == 1) {
            sb.append(1);
            return;
        } else if (n == 2) {
            sb.append(1);
            return;
        } else if (n == 3) {
            sb.append(2);
            return;
        }
        sb.append(fibonacci(n));
    }

    static int fibonacci(int i) {
        if (fibo[i] != 0) {
            return fibo[i];
        }
        return fibo[i] = fibonacci(i - 1) + fibonacci(i - 2);
    }
}

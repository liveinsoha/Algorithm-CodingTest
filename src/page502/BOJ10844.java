package page502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10844 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] evenStair = new long[10];
    static long[] oddStair = new long[10];
    static int n;
    private static final long MOD = 1000000000L;

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
        initStairArr();
    }

    static void solve() {
        long sum = 0;
        if (n % 2 == 0) {
            for (int i = 0; i <= 9; i++) {
                sum  = (sum + evenStair[i]) % MOD;
            }
        } else {
            for (int i = 0; i <= 9; i++) {
                sum = (sum + oddStair[i]) % MOD;
            }
        }
        sb.append(sum);
    }

    static void initStairArr() {
        oddStair[0] = 0;
        for (int i = 1; i <= 9; i++) {
            oddStair[i] = 1;
        }
        if (n >= 2) {
            for (int i = 2; i <= n; i++) {
                if (i % 2 == 0) {
                    initEven();
                } else {
                    initOdd();
                }
            }
        }
    }

    static void initEven() {
        evenStair[0] = oddStair[1];
        for (int i = 1; i <= 8; i++) {
            evenStair[i] = (oddStair[i - 1] + oddStair[i + 1]) % MOD;
        }
        evenStair[9] = oddStair[8];
    }

    static void initOdd() {
        oddStair[0] = evenStair[1];
        for (int i = 1; i <= 8; i++) {
            oddStair[i] = (evenStair[i - 1] + evenStair[i + 1]) % MOD;
        }
        oddStair[9] = evenStair[8];
    }
}

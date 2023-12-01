package page469조합확률바운더리클때분수로구함;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int total;

    static int n;
    static int k;
    static int[] rocks;

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
        rocks = new int[n];
        total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rocks[i] = Integer.parseInt(st.nextToken());
            total += rocks[i];
        }
        k = Integer.parseInt(br.readLine());
    }

    static double getProbabilitySum() {
        double probabilitySum = 0;
        for (int i = 0; i < n; i++) {
            double prob = 1;
            double bunja = rocks[i];
            double bunmo = total;
            for (int j = 0; j < k; j++) {
                prob *= (bunja--) / (bunmo--);
            }
            probabilitySum += prob;
        }
        return probabilitySum;
    }

    static void solve() {
        sb.append(getProbabilitySum());
    }
}
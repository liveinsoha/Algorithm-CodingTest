package page200;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ1715 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static long ans;

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

        for (int i = 1; i <= n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
    }

    static void solve() {
        while (pq.size() > 1) {
            int now = pq.poll() + pq.poll();
            ans += now;
            pq.add(now);
        }
        sb.append(ans);
    }
}

package page207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1931 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int endOfTime;
    static int count;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<int[]> pq = new PriorityQueue<>((arr1, arr2) -> {
        if (arr1[1] != arr2[1]) {
            return arr1[1] - arr2[1];
        } else {
            return arr1[0] - arr2[0];
        }
    });


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

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        count = 0;
        endOfTime = 0;

    }

    static void solve() {
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            if (next[0] >= endOfTime) {
                count++;
                endOfTime = next[1];
            }
        }
        sb.append(count);
    }
}

package page261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18352 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[] visited;
    static Queue<int[]> q = new LinkedList<>();
    static ArrayList<Integer>[] graph;
    static int n;
    static int m;
    static int k;
    static int x;
    static List<Integer> ans = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        solution();
        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            for (int i : ans) {
                System.out.println(i);
            }
        }
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }
    }

    static void solve() {
        bfs(x);
        Collections.sort(ans);
    }

    static void bfs(int s) {
        q.add(new int[]{s, 0});

        while (!q.isEmpty()) {
            int[] next = q.poll();
            if (visited[next[0]]) {
                continue;
            }
            if (next[1] > k) {
                break;
            }
            visited[next[0]] = true;
            if (next[1] == k) {
                ans.add(next[0]);
            }
            for (int i : graph[next[0]]) {
                if (!visited[i]) {
                    q.add(new int[]{i, next[1] + 1});
                }
            }
        }
    }
}

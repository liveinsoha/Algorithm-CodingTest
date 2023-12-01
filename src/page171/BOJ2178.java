package page171;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static boolean[][] field;
    static boolean[][] visited;
    static int n;
    static int m;
    static int minDepth;
    static Queue<int[]> q = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        field = new boolean[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];

        String str;
        for (int i = 1; i <= n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                field[i][j + 1] = cast(str.charAt(j));
            }
        }
        minDepth = Integer.MAX_VALUE;
    }

    static boolean cast(int c) {
        if (c == '1') {
            return true;
        }
        return false;
    }

    static void solve() {
        sb.append(bfs(1, 1));
    }

    static int bfs(int i, int j) {
        int[] now;
        q.add(new int[]{i, j, 1});
        while (!q.isEmpty()) {
            now = q.poll();
            if (visited[now[0]][now[1]]) {
                continue;
            }
            visited[now[0]][now[1]] = true;

            if (now[0] == n && now[1] == m) {
                return now[2];
            }

            if (canGo(now[0] + 1, now[1])) {
                q.add(new int[]{now[0] + 1, now[1], now[2] + 1});
            }
            if (canGo(now[0], now[1] + 1)) {
                q.add(new int[]{now[0], now[1] + 1, now[2] + 1});
            }
            if (canGo(now[0] - 1, now[1])) {
                q.add(new int[]{now[0] - 1, now[1], now[2] + 1});
            }
            if (canGo(now[0], now[1] - 1)) {
                q.add(new int[]{now[0], now[1] - 1, now[2] + 1});
            }
        }
        return 0;
    }

    static boolean canGo(int i, int j) {
        if (i >= 1 && i <= n && j >= 1 && j <= m && field[i][j] && !visited[i][j]) {
            return true;
        }
        return false;
    }
}

package page358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int v;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        v = Integer.parseInt(br.readLine());
        graph = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= v; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        floydWarshall();
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    static void floydWarshall() {
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (graph[i][k] == 0 || graph[k][j] == 0) {
                        continue;
                    }
                    graph[i][j] = 1;
                }
            }
        }
    }
}

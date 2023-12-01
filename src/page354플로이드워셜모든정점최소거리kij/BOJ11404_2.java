package page354플로이드워셜모든정점최소거리kij;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*모드 정점 순서쌍의 최단거리를 구할 수 있는 플로이드 워셜, V^3의 시간복잡도를 가진다
 * 음수가 있어도 가능하다*/
public class BOJ11404_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[][] minDistance;
    static int v;
    static int e;

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
        e = Integer.parseInt(br.readLine());
        minDistance = new long[v + 1][v + 1];

        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                minDistance[i][j] = Long.MAX_VALUE;
            }
        }

        for (int i = 1; i <= e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            minDistance[a][b] = Math.min(minDistance[a][b], w);
        }
    }

    static void solve() {
        floydWarshall();
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (minDistance[i][j] == Long.MAX_VALUE) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(minDistance[i][j]).append(" ");
            }
            sb.append("\n");
        }
    }

    static void floydWarshall() {
        for (int k = 1; k <= v; k++) {
            for (int s = 1; s <= v; s++) {
                for (int e = 1; e <= v; e++) {
                    if (minDistance[s][k] == Long.MAX_VALUE || minDistance[k][e] == Long.MAX_VALUE) {
                        continue;
                    }
                    minDistance[s][e] = Math.min(minDistance[s][e], minDistance[s][k] + minDistance[k][e]);
                }
            }
        }
    }
}

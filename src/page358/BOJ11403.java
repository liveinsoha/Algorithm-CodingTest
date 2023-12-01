package page358;

import java.io.*;
import java.util.*;

public class BOJ11403 {

    static long[][] dist;
    final static int INF = Integer.MAX_VALUE;
    static int n;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new long[n + 1][n + 1];
        StringTokenizer st;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = INF;
            }
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 0)
                    continue;
                else
                    dist[i][j] = k;
            }
        }
        floydwarshall();
        printAll();
    }

    private static void printAll() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF)
                    sb.append(0).append(" ");
                else
                    sb.append(1).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void floydwarshall() {

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}

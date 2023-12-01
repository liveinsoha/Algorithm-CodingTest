package page361;

import java.io.*;
import java.util.*;

public class BOJ1389 {
    static int n;
    static int m;
    static long[][] dist;
    final static int INF = Integer.MAX_VALUE;
    static long[] sumArr;

    public static void main(String[] args) throws Exception {
        solution();
    }

    private static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dist = new long[n + 1][n + 1];
        sumArr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;
                dist[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }
      // printAll();
        floydwarshall();
       // printAll();
        sumEach();
        //System.out.println(Arrays.toString(sumArr));
        System.out.println(findMinIdx());

    }

    private static long findMinIdx() {
        long min = sumArr[1];
        int idx = 1;
        for (int i = 1; i <= n; i++) {
            if (sumArr[i] < min) {
                min = sumArr[i];
                idx = i;
            }
        }
        return idx;
    }

    private static void printAll() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] == INF)
                    sb.append(0).append(" ");
                else
                    sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void sumEach() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    continue;
                sumArr[i] += dist[i][j];
            }
        }
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

package page304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1516 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] myTime;
    static int[] beforeTime;
    static int[] inDegree;
    static ArrayList<Integer>[] graph;
    static int n;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        solution();
        for (int i = 1; i <= n; i++) {
            System.out.println(myTime[i]);
        }
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        myTime = new int[n + 1];
        beforeTime = new int[n + 1];
        inDegree = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            myTime[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int preBuild = Integer.parseInt(st.nextToken());
                if (preBuild == -1) {
                    break;
                }
                graph[preBuild].add(i);
                inDegree[i]++;
            }
        }
    }

    static void solve() {
        topology();
    }

    static void topology() {
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            myTime[now] += beforeTime[now];

            for (int k : graph[now]) {
                inDegree[k]--;
                beforeTime[k] = Math.max(myTime[now], beforeTime[k]);
                if (inDegree[k] == 0) {
                    q.add(k);
                }
            }
        }
    }
}


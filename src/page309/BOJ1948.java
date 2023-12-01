package page309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1948 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;

    static int startCity;
    static int endCity;
    static int[] myTime;
    static int[] beforeTime;
    static int[] inDegree;
    static ArrayList<int[]>[] graph;
    static ArrayList<Integer>[] contribute;
    static Queue<Integer> q = new LinkedList<>();

    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
       solution();
       System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
        sb.append(myTime[endCity]).append("\n");
        sb.append(count -1);
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        myTime = new int[n + 1];
        beforeTime = new int[n + 1];
        inDegree = new int[n + 1];

        graph = new ArrayList[n + 1];
        contribute = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            contribute[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, w});
            inDegree[b]++;
        }

        st = new StringTokenizer(br.readLine());
        startCity = Integer.parseInt(st.nextToken());
        endCity = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        count = 0;
    }

    static void solve() {
        topology();
        dfsToFindMaxRoad(endCity);
    }

    static void topology() { //가장 늦은 도착시간을 구하기 위한 위상정력과 동시에 늦는데 기여한 정점을 contribute에 담는다.
        q.add(startCity);

        while (!q.isEmpty()) {
            int now = q.poll();
            myTime[now] += beforeTime[now];
            for (int[] next : graph[now]) {
                inDegree[next[0]]--;
                if (myTime[now] + next[1] > beforeTime[next[0]]) {
                    contribute[next[0]].clear(); //기여 정점 갱신
                    contribute[next[0]].add(now);
                    beforeTime[next[0]] = myTime[now] + next[1]; 
                } else if (myTime[now] + next[1] == beforeTime[next[0]]) {
                    contribute[next[0]].add(now); //가여 정점 누적
                }
                if (inDegree[next[0]] == 0) {
                    q.add(next[0]);
                }
            }
        }
    }

    static void dfsToFindMaxRoad(int s) { //기여 정점을 역추적 하며 도로 개수를 카운팅한다. 
        count++;
        if (visited[s]) {
            return;
        }
        visited[s] = true;
        for (int i : contribute[s]) {
            dfsToFindMaxRoad(i);
        }
    }
}

package page175;

import java.util.*;

/*트리는 사이클이 존재하지 않는 그래프. 모든 노드가 시선에 따라 루트노드가 될 수 있다*/
public class BOJ1167 {

    static LinkedList<int[]>[] Arr;
    static boolean[] visited;
    static int node;
    static int maxlen = 0;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Arr = new LinkedList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            Arr[i] = new LinkedList<int[]>();
        }
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt();
            while(true) {
                int node = sc.nextInt();
                if (node == -1)
                    break;
                Arr[v].add(new int[]{node, sc.nextInt()});
            }
        }

        dfs(1, 0);
        visited = new boolean[n + 1];

        dfs(node, 0);
        System.out.println(maxlen);

    }
    public static void dfs(int s, int w) {

        if (visited[s])
            return;

        visited[s] = true;

        int child = 0;
        for (int[] arr : Arr[s]) {
            if (!visited[arr[0]]) {
                dfs(arr[0], w + arr[1]);
                child++;
            }
        }
        if (child == 0 && w > maxlen) {
            maxlen = w;
            node = s;
        }
    }
}


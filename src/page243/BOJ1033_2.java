package page243;

import java.util.*;
import java.io.*;

public class BOJ1033_2 {

    static Queue<int[]> queue = new LinkedList<int[]>();
    static boolean[] exist;
    static int[] per;
    static int n;

    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        exist = new boolean[n];
        per = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        exist[a] = true;
        exist[b] = true;
        per[a] = c / gcd(c, d);
        per[b] = d / gcd(c, d);

        for (int i = 1; i < n - 1; i++) {
            int[] arr = new int[4];
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            arr[0] = p;
            arr[1] = q;
            arr[2] = r / gcd(r, s);
            arr[3] = s / gcd(r, s);

            int k = check(arr);
            if (k == -1) {
                queue.add(arr);
                continue;
            }
            work(arr, k);
        }

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int k = check(arr);
            if (k == -1) {
                queue.add(arr);
                continue;
            }
            work(arr, k);
        }
        int gcd =  getGCD(per);
        for (int i = 0; i < n; i++) {
            per[i] = per[i] / gcd;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(per[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static int getGCD(int[] arr) {
        if (arr.length == 1)
            return arr[0];
        int v = gcd(arr[0], arr[1]);
        for (int i = 1; i < arr.length - 1; i++) {
            v = gcd(v, arr[i + 1]);
        }
        return v;
    }

    public static void work(int[] arr, int k) {
        exist[arr[0]] = true;
        exist[arr[1]] = true;
        if (arr[0] == k) {
            int temp = per[arr[0]];
            for (int i = 0; i < n; i++) {
                per[i] = per[i] * arr[2];
            }
            per[arr[1]] = temp * arr[3];
        } else {
            int temp = per[arr[1]];
            for (int i = 0; i < n; i++) {
                per[i] = per[i] * arr[3];
            }
            per[arr[0]] = temp * arr[2];
        }
    }

    public static int check(int[] arr) {
        if (exist[arr[0]])
            return arr[0];
        else if (exist[arr[1]])
            return arr[1];
        else
            return -1;
    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }
}
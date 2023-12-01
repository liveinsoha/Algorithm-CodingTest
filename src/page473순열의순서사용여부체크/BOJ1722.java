package page473순열의순서사용여부체크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1722 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();
    static long[] factorial;
    static boolean[] used;
    static int n;
    static long k;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        initAndSolve();
    }

    static void initAndSolve() throws IOException {
        n = Integer.parseInt(br.readLine());
        used = new boolean[n + 1];
        nums = new int[n + 1];
        initFactorial();

        st = new StringTokenizer(br.readLine());
        int key = Integer.parseInt(st.nextToken());

        if (key == 1) {
            k = Long.parseLong(st.nextToken());
            solve1();
        } else {
            for (int i = n; i >= 1; i--) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            solve2();
        }


    }

    static void solve1() {
        for (int i = n; i >= 1; i--) {
            setNth(i);
        }
        sb.append("\n");
    }

    static void solve2() {
        sb.append(getTotalCount());
    }

    static long getTotalCount() {
        long totalCount = 0;
        for (int i = n; i >= 1; i--) {
            totalCount += getCount(nums[i]) * factorial[i - 1];
        }
        return totalCount + 1;
    }

    static int getCount(int n) {
        int i = 1;
        int count = 0;
        while (i < n) {
            if (!used[i++]) {
                count++;
            }
        }
        used[i] = true;
        return count;
    }


    static void initFactorial() {
        factorial = new long[n];
        for (int i = 1; i <= n - 1; i++) {
            factorial[i] = factorial(i);
        }
    }

    static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    static void setNth(int n) {
        int q;
        int th;
        if (k < factorial[n - 1] || n == 1) {
            th = 1;
            setNum(th);
        } else {
            if (k % factorial[n - 1] == 0) {
                q = (int) (k / factorial[n - 1]);
                th = q;
                k -= (factorial[n - 1] * (q - 1));
                setNum(th);
            } else {
                q = (int) (k / factorial[n - 1]);
                th = q + 1;
                k -= factorial[n - 1] * q;
                setNum(th);
            }
        }
    }

    static void setNum(int th) {
        int count = 0;
        int i = 1;
        while (count < th) {
            if (!used[i++]) {
                count++;
            }
        }
        used[i - 1] = true;
        sb.append(i - 1).append(" ");
    }
}

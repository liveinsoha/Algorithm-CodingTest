package page539;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class BOJ14003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] num;
    static int[] notLIS;
    static int[] myLISIdx;
    static int LISLen;
    static int lastIdx;
    static int n;
    static int maxIdx;
    static int[] LIS;

    public static void main(String[] args) throws IOException {
        solution();
        System.out.println(sb);
    }

    static void solution() throws IOException {
        init();
        solve();
    }

    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        num = new int[n];
        notLIS = new int[n];
        myLISIdx = new int[n];

        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        findLISLen();
        LISLen = lastIdx + 1;
        sb.append(LISLen).append("\n");
        getLIS();
        for (int i = 0; i < LISLen; i++) {
            sb.append(LIS[i]).append(" ");
        }
    }

    static void findLISLen() {
        notLIS[0] = num[0];
        lastIdx = 0;
        myLISIdx[0] = 0;
        for (int i = 1; i < n; i++) {
            if (num[i] > notLIS[lastIdx]) {
                lastIdx++;
                notLIS[lastIdx] = num[i];
                myLISIdx[i] = lastIdx; //자신의 LIS인덱스를 저장한다
                maxIdx = i; //LIS의 맨 끝 갱신. -> LIS얻을 때 맨 끝 인덱스부터 얻는다
            } else {
                int lo = 0;
                int hi = lastIdx;
                int mid;
                while (lo < hi) {
                    mid = (lo + hi) / 2;
                    if (num[i] > notLIS[mid]) {
                        lo = mid + 1;
                    } else {
                        hi = mid;
                    }
                }
                notLIS[lo] = num[i]; //길이를 위한 배열일 뿐 LIS는 아니다.
                myLISIdx[i] = lo; //자신의 LIS에서 인덱스를 저장한다
            }
        }
    }

    static void getLIS() {
        int LISIdx = LISLen - 1;
        LIS = new int[LISLen];
        for (int i = maxIdx; i >= 0; i--) {
            if (myLISIdx[i] == LISIdx) {
                LIS[LISIdx] = num[i];
                LISIdx--;
            }
        }
    }
}

package coding;

public class PowerOfThree {
    public String ableToGet(int x, int y) {
        int[] a = get(x);
        int[] b = get(y);
        int max = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) max = Math.max(i, max);
        }
        for (int i = 0; i < b.length; i++) {
            if (b[i] > 0) max = Math.max(i, max);
        }
        for (int i = 0; i <= max; i++) {
            if ((a[i] > 0) == (b[i] > 0)) return "Impossible";
        }
        return "Possible";
    }

    static int[] THREE;
    static {
        THREE = new int[20];
        THREE[0] = 1;
        for (int i = 1; i < THREE.length; i++) {
            THREE[i] = THREE[i - 1] * 3;
        }
    }

    int[] get(int n) {
        if (n < 0) n = -n;
        int[] ret = new int[THREE.length];
        for (int i = THREE.length - 1; i >= 0; i--) {
            if (Math.abs(n - THREE[i]) < n) {
                n = Math.abs(n - THREE[i]);
                ret[i] = 1;
            }
        }
        return ret;
    }
}

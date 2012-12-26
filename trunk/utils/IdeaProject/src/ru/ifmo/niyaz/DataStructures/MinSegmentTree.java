package DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 02.10.12
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */
public class MinSegmentTree {
    int[] min;
    int[] minId;
    int n;

    public MinSegmentTree(int n) {
        this.n = Integer.highestOneBit(n) << 1;
        min = new int[this.n * 2];
        minId = new int[this.n * 2];
        for (int i = 0; i < n; i++) {
            minId[i + n] = i;
        }
        for (int i = 0; i < n; i++) {
            set(i, Integer.MAX_VALUE);
        }
    }

    public void set(int x, int y) {
        x += n;
        min[x] = y;
        while (x > 1) {
            x >>= 1;
            int left = min[x << 1];
            int right = min[(x << 1) | 1];
            if (left <= right) {
                min[x] = left;
                minId[x] = minId[x << 1];
            } else {
                min[x] = right;
                minId[x] = minId[(x << 1) | 1];
            }
        }
    }

    public void add(int x, int y) {
        x += n;
        min[x] += y;
        while (x > 1) {
            x >>= 1;
            int left = min[x << 1];
            int right = min[(x << 1) | 1];
            if (left <= right) {
                min[x] = left;
                minId[x] = minId[x << 1];
            } else {
                min[x] = right;
                minId[x] = minId[(x << 1) | 1];
            }
        }
    }

    public int getMin(int left, int right) {
        --right;
        left += n;
        right += n;
        int ret = Integer.MAX_VALUE;
        while (left <= right) {
            if ((left & 1) == 1) {
                ret = Math.min(ret, min[left]);
                left++;
            }
            if ((right & 1) == 0) {
                ret = Math.min(ret, min[right]);
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return ret;
    }

}

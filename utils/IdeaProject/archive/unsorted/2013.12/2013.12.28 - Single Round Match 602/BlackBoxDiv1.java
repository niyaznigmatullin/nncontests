package coding;

import ru.ifmo.niyaz.math.MathUtils;

public class BlackBoxDiv1 {

    static final int MOD = 1000000007;

    static final int[] DX = {1, 0, -1, 0};
    static final int[] DY = {0, 1, 0, -1};

    static int get(int n, int m) {
        int ret = 0;
        for (int mask = 0; mask < 1 << n * m; mask++) {
            boolean[][] c = new boolean[n][m];
            for (int i = 0, p = mask; i < n; i++) {
                for (int j = 0; j < m; j++, p >>= 1) {
                    c[i][j] = (p & 1) == 1;
                }
            }
            boolean ok = false;
            all2: for (int i1 = 0; i1 < n; i1++) {
                all:
                for (int j1 = 0; j1 < m; j1++) {
                    for (int start = 0; start < m; start++) {
                        int cd = 0;
                        int cx = 0;
                        int cy = start;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (c[cx][cy]) {
                                cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                            } else {
                                cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        int lastX = cx;
                        int lastY = cy;
                        cx = 0;
                        cy = start;
                        cd = 0;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (cx != i1 && cy != j1) {
                                if (c[cx][cy]) {
                                    cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                                } else {
                                    cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                                }
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        if (cx != lastX && cy != lastY) {
                            continue all;
                        }
                    }
                    for (int start = 0; start < m; start++) {
                        int cd = 2;
                        int cx = n - 1;
                        int cy = start;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (c[cx][cy]) {
                                cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                            } else {
                                cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        int lastX = cx;
                        int lastY = cy;
                        cd = 2;
                        cx = n - 1;
                        cy = start;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (cx != i1 && cy != j1) {
                                if (c[cx][cy]) {
                                    cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                                } else {
                                    cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                                }
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        if (cx != lastX && cy != lastY) {
                            continue all;
                        }
                    }
                    for (int start = 0; start < n; start++) {
                        int cd = 1;
                        int cx = start;
                        int cy = 0;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (c[cx][cy]) {
                                cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                            } else {
                                cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        int lastX = cx;
                        int lastY = cy;
                        cd = 1;
                        cx = start;
                        cy = 0;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (cx != i1 && cy != j1) {
                                if (c[cx][cy]) {
                                    cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                                } else {
                                    cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                                }
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        if (cx != lastX && cy != lastY) {
                            continue all;
                        }
                    }

                    for (int start = 0; start < n; start++) {
                        int cd = 3;
                        int cx = start;
                        int cy = m - 1;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (c[cx][cy]) {
                                cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                            } else {
                                cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        int lastX = cx;
                        int lastY = cy;
                        cd = 3;
                        cx = start;
                        cy = m - 1;
                        while (cx >= 0 && cx < n && cy >= 0 && cy < m) {
                            if (cx != i1 && cy != j1) {
                                if (c[cx][cy]) {
                                    cd = (cd + ((cd & 1) == 1 ? 1 : -1)) & 3;
                                } else {
                                    cd = (cd + ((cd & 1) == 0 ? 1 : -1)) & 3;
                                }
                            }
                            cx += DX[cd];
                            cy += DY[cd];
                        }
                        if (cx != lastX && cy != lastY) {
                            continue all;
                        }
                    }
                    ok = true;
                    break all2;
                }
            }
            if (ok) {
                ++ret;
            }
        }
        return ret;
    }

    public int count(int N, int M) {
//        for (int i = 1; i <= 4; i++) {
//            for (int j = i; j <= 4; j++) {
//                System.out.println(i + " " + j + " " + get(i, j));
//            }
//        }
        System.out.println(get(2, 2));
        return MathUtils.modPow(2, N * M, MOD);
    }
}

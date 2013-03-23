package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Task1299 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int energy = in.nextInt();
        int x = Math.min(energy, 100);
        int ammo = in.nextInt();
        int shoot = Math.min(ammo, 20);
        String type = in.next();
        int enemies = in.nextInt();
        int enemyAverageHP = in.nextInt();
        int angle = in.nextInt();
        if (type.equals("A")) {
            int numberOfMyRobots = in.nextInt();
            int myAverageHP = in.nextInt();
            if (enemies * enemyAverageHP * 3 < numberOfMyRobots * myAverageHP) {
                advance(out, x, shoot, enemies, angle);
            } else {
                retreat(out, x, shoot, enemies, angle);
            }
        } else if (type.equals("G")) {
            guard(out, x, shoot, enemies, angle);
        } else if (type.equals("P")) {
            int angleToGoal = in.nextInt();
            boolean forward = true;
            if (Math.abs(angleToGoal) > 90) {
                angleToGoal = angleToGoal - Integer.signum(angleToGoal) * 180;
                forward = false;
            }
            if (enemies > 0) {
                defend(out, x, ammo, shoot, enemies, angle);
            } else if (Math.abs(angleToGoal) <= 20) {
                out.println((forward ? "FRONT " : "BACKWARD ") + x);
            } else if (angleToGoal > 0) {
                out.println("LEFT " + x);
            } else {
                out.println("RIGHT " + x);
            }
        } else {
            defend(out, x, ammo, shoot, enemies, angle);
        }
    }

    private void defend(FastPrinter out, int x, int ammo, int shoot, int enemies, int angle) {
        if (enemies * 20 >= ammo) {
            retreat(out, x, shoot, enemies, angle);
        } else {
            guard(out, x, shoot, enemies, angle);
        }
    }

    private void guard(FastPrinter out, int x, int shoot, int enemies, int angle) {
        if (enemies == 0) {
            out.println("STOP");
        } else if (Math.abs(angle) < 5) {
            out.println("FIRE " + shoot);
        } else if (angle >= 5) {
            out.println("LEFT " + x);
        } else {
            out.println("RIGHT " + x);
        }
    }

    private void retreat(FastPrinter out, int x, int shoot, int enemies, int angle) {
        if (Math.abs(angle) >= 5 || enemies == 0) {
            out.println("BACKWARD " + x);
        } else {
            out.println("FIRE " + shoot);
        }
    }

    private void advance(FastPrinter out, int x, int shoot, int enemies, int angle) {
        if (Math.abs(angle) >= 10 || enemies == 0) {
            out.println("FRONT " + x);
        } else {
            out.println("FIRE " + shoot);
        }
    }
}

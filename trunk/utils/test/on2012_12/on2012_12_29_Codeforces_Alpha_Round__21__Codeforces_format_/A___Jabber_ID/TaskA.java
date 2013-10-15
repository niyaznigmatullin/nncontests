package lib.test.on2012_12.on2012_12_29_Codeforces_Alpha_Round__21__Codeforces_format_.A___Jabber_ID;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int pos = s.indexOf('@');
        if (pos < 0) {
            out.println("NO");
            return;
        }
        String userName = s.substring(0, pos);
        if (!isUserName(userName)) {
            out.println("NO");
            return;
        }
        s = s.substring(pos + 1);
        pos = s.indexOf('/');
        String hostName = pos < 0 ? s : s.substring(0, pos);
        if (hostName.length() < 1 || hostName.length() > 32) {
            out.println("NO");
            return;
        }
        while (true) {
            int pos2 = hostName.indexOf('.');
            String z = pos2 < 0 ? hostName : hostName.substring(0, pos2);
            if (!isUserName(z)) {
                out.println("NO");
                return;
            }
            if (pos2 < 0) {
                break;
            }
            hostName = hostName.substring(pos2 + 1);
        }
        if (pos >= 0) {
            String resource = s.substring(pos + 1);
            if (!isUserName(resource)) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
    }

    private boolean isUserName(String userName) {
        if (userName.length() < 1 || userName.length() > 16) {
            return false;
        }
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
}

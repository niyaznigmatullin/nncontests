package coding;

import java.util.ArrayList;
import java.util.List;

public class UndoHistory {
    public int minPresses(String[] lines) {
        List<String> undo = new ArrayList<String>();
        String current = "";
        undo.add(current);
        int ans = 0;
        for (String e : lines) {
            String best = null;
            for (String f : undo) {
                if (e.startsWith(f)) {
                    if (best == null || f.length() > best.length()) {
                        best = f;
                    }
                }
            }
            if (e.startsWith(current)) {
                if (current.length() + 2 >= best.length()) {
                    best = current;
                } else {
                    ans += 2;
                }
            } else {
                ans += 2;
            }
            undo.add(best);
            for (int i = best.length(); i < e.length(); i++) {
                undo.add(e.substring(0, i));
                ++ans;
            }
            current = e;
        }
        return ans + lines.length;
    }
}

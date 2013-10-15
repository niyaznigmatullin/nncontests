package lib.test.on2013_08.on2013_08_27_Single_Round_Match_589.GearsDiv1;



import ru.ifmo.niyaz.graphalgorithms.KuhnMatchingGraph;

public class GearsDiv1 {
    public int getmin(String color, String[] graph) {
        int ans = Integer.MAX_VALUE;
        for (char c : "RGB".toCharArray()) {
            for (char d : "RGB".toCharArray()) {
                if (c >= d) {
                    continue;
                }
                KuhnMatchingGraph g = new KuhnMatchingGraph(color.length(), color.length());
                for (int i = 0; i < color.length(); i++) {
                    if (color.charAt(i) != c) {
                        continue;
                    }
                    for (int j = 0; j < color.length(); j++) {
                        if (color.charAt(j) != d) {
                            continue;
                        }
                        if (graph[i].charAt(j) != 'Y') {
                            continue;
                        }
                        g.addEdge(i, j);
                    }
                }
                int cur = g.getMaximalMatching();
                ans = Math.min(ans, cur);
            }
        }
        return ans;
    }
}

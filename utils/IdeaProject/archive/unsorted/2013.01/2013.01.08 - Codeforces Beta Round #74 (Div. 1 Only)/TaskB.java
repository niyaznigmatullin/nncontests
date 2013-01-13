package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskB {

    static abstract class Widget {
        Size size;

        abstract Size getSize();
    }

    static class Size {
        long width;
        long height;

        Size(long width, long height) {
            this.width = width;
            this.height = height;
        }
    }

    static class VBoxWidget extends BoxWidget {

        @Override
        Size getSize() {
            if (size != null) {
                return size;
            }
            if (c.isEmpty()) {
                return size = new Size(0, 0);
            }
            long maxW = 0;
            long sumH = 0;
            for (Widget e : c) {
                Size f = e.getSize();
                maxW = Math.max(maxW, f.width);
                sumH += f.height;
            }
            return size = new Size(2 * border + maxW, 2 * border + (c.size() - 1) * spacing + sumH);
        }
    }

    static class HBoxWidget extends BoxWidget  {

        @Override
        Size getSize() {
            if (size != null) {
                return size;
            }
            if (c.isEmpty()) {
                return size = new Size(0, 0);
            }
            long maxH = 0;
            long sumW = 0;
            for (Widget e : c) {
                Size f = e.getSize();
                maxH = Math.max(maxH, f.height);
                sumW += f.width;
            }
            return size = new Size(2 * border + (c.size() - 1) * spacing + sumW, 2 * border + maxH);
        }
    }


    static class SimpleWidget extends Widget {
        Size size;

        SimpleWidget(int width, int height) {
            size = new Size(width, height);
        }

        @Override
        Size getSize() {
            return size;
        }
    }

    static abstract class BoxWidget extends Widget {
        long border;
        long spacing;
        List<Widget> c;

        public BoxWidget() {
            c = new ArrayList<Widget>();
        }

        public void addC(Widget w) {
            c.add(w);
        }

        public long getBorder() {
            return border;
        }

        public void setBorder(long border) {
            this.border = border;
        }

        public long getSpacing() {
            return spacing;
        }

        public void setSpacing(long spacing) {
            this.spacing = spacing;
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Widget> widgets = new TreeMap<String, Widget>();
        for (int i = 0; i < n; i++) {
            String type = in.next();
            if (type.equals("Widget")) {
                StringTokenizer st = new StringTokenizer(in.next(), "(),");
                String name = st.nextToken();
                int w = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());
                widgets.put(name, new SimpleWidget(w, h));
            } else if (type.equals("VBox")) {
                String name = in.next();
                widgets.put(name, new VBoxWidget());
            } else if (type.equals("HBox")) {
                String name = in.next();
                widgets.put(name, new HBoxWidget());
            } else {
                StringTokenizer st = new StringTokenizer(type, ".()");
                String name = st.nextToken();
                String type2 = st.nextToken();
                BoxWidget boxWidget = (BoxWidget) widgets.get(name);
                if (type2.equals("pack")) {
                    String name2 = st.nextToken();
                    boxWidget.addC(widgets.get(name2));
                } else if (type2.equals("set_border")) {
                    boxWidget.setBorder(Integer.parseInt(st.nextToken()));
                } else {
                    boxWidget.setSpacing(Integer.parseInt(st.nextToken()));
                }
            }
        }
        for (Map.Entry<String, Widget> entry : widgets.entrySet()) {
            Size e = entry.getValue().getSize();
            out.println(entry.getKey() + " " + e.width + " " + e.height);
        }
    }
}

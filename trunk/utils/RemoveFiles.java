import java.io.*;
import org.apache.commons.io.*;

import static org.apache.commons.io.FileUtils.*;

public class RemoveFiles {
    public static void main(String[] args) throws Exception {
        File curDir = new File(".");
        File[] files = curDir.listFiles();
        for (File e : files) {
            if (!e.isDirectory() || e.isHidden()) {
                continue;
            }            
            File[] f2 = e.listFiles();
            for (File f : f2) {
                String name = f.getName();
                if (name.equals("src")) {
                    for (File ff : f.listFiles()) {
                        moveFile(ff, new File(e, ff.getName()));
                    }
                    f.delete();
                }
            }
        }
    }

    static void search(File f, String end) {
        if (!f.isDirectory()) {String name = f.getName();
        if (!name.endsWith(end)) {
             System.err.println("found file " + f.getPath());
        }
            return;
        }        
        File[] list = f.listFiles();
        for (File e : list) {
            search(e, end);
        }        
    }

    static void remove(File f) {
        if (!f.isDirectory()) {            
            if (f.getName().endsWith("java")) {
                return;
            }
            f.delete();
            return;
        }
        File[] list = f.listFiles();
        for (File e : list) {
            remove(e);
        }
    }
}
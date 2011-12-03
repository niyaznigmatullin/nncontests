import java.io.*;
import org.apache.commons.io.*;

import static org.apache.commons.io.FileUtils.*;

public class RemoveFiles {
    public static void main(String[] args) throws Exception {
        for (File curDir : new File(".").listFiles()) {

        File[] files = curDir.listFiles();
        if (!curDir.isDirectory() || curDir.isHidden()) {
         	continue;
        }
        removeMask(curDir, ".java", ".cpp", ".dpr", ".py");
        for (File e : files) {
            if (!e.isDirectory() || e.isHidden()) {
                continue;
            }
            if (e.getName().startsWith(".") || e.getName().equals("bin")) {
            	System.err.println(e.getName());
			   	remove(e);
            	continue;
            }
        }
        for (File e : curDir.listFiles()) {
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

    static void removeMask(File f, String... s) {
    	if (!f.isDirectory()) {
    		for (String e : s) {
	    		if (f.getName().indexOf(e) >= 0) {
					return;
	    		}
	    	}
            f.delete();
            return;
        }
        File[] list = f.listFiles();
        for (File e : list) {
            removeMask(e, s);
        }    	
    }

        
    static void remove(File f) {
        if (!f.isDirectory()) {
        	System.err.println("removing file " + f.getPath());
            f.delete();
            return;
        }
        File[] list = f.listFiles();
        for (File e : list) {
            remove(e);
        }
        System.err.println("removing folder " + f.getPath());
        f.delete();
    }
}
java -javaagent:../lib/cojac.jar -cp ../out/production/IdeaProject -Xmx1G -Xss256M Main < %1 > %1.out
cp ../src/Main.java .

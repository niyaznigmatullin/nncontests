java -javaagent:../lib/cojac.jar -cp ../out/production/IdeaProject -Xmx512M -Xss128M Main < %1 > %1.out
cp ../src/Main.java .

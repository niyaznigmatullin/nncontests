g++ -O2 -Wall -Wl,--stack=32000000 -o 1307.exe 1307.cpp || exit

while true
do
./gen.exe > in
./1307.exe < in > out.cpp || exit
g++ -O2 -Wall -Wl,--stack=32000000 -o out.exe out.cpp || exit
./out.exe > out || exit
diff out in || exit
echo "OK"
done
dcc32 -cc gen.dpr
g++ -O2 -Wl,--stack=256000000 -o a.exe a.cpp
g++ -O2 -o b.exe b.cpp
while ((2>1))
do
    ./gen.exe > input
    time ./a.exe < input > out1
    echo "OK"
done
dcc32 -cc gen.dpr
g++ -O2 -o a.exe a.cpp
g++ -O2 -o b.exe b.cpp
while ((2>1))
do
    ./gen.exe > input
    ./a.exe < input > out1
    ./b.exe < input > out2
    diff out1 out2 || exit
    echo "OK"
done
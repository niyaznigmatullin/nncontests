g++ -O2 -o z.exe z.cpp
g++ -O2 -o z1.exe z1.cpp
dcc32 -cc zgen.dpr
while ((2>1))
do
    ./zgen.exe > z.in
    ./z.exe < z.in > z1.out
    ./z1.exe < z.in > z2.out
    diff z1.out z2.out || exit
    echo "OK"
done
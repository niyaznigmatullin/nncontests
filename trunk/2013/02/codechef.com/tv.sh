g++ -O2 -Wl,--stack=32000000 -o v.exe v.cpp
g++ -O2 -Wl,--stack=32000000 -o vs.exe vs.cpp
dcc32 -cc vgen.dpr

while ((2>1))
do
    ./vgen.exe > v.in
    ./v.exe < v.in > v.out
#    ./vs.exe < v.in > v2.out
#    diff v.out v2.out || exit
    echo "OK"
done
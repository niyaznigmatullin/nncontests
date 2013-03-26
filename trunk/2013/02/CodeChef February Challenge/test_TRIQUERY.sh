g++ -O2 -o t.exe t.cpp
g++ -O2 -o ts.exe ts.cpp
fpc tgen.pas

while ((2>1))
do
    ./tgen.exe > t.in
    ./t.exe < t.in > t1.out
    ./ts.exe < t.in > t2.out
    diff t1.out t2.out || exit
    echo "OK"
done
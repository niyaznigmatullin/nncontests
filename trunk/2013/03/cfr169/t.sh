g++ -O2 -o e.exe e.cpp
g++ -O2 -o es.exe es.cpp
fpc egen.pas

while true
do
    ./egen.exe > e.in
    ./e.exe < e.in > e1.out
    ./es.exe < e.in > e2.out
    diff e1.out e2.out || exit
    echo "OK"
done

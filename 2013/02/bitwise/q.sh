g++ -O2 -o q.exe q.cpp
g++ -O2 -o qs.exe QUERY_STUPID.cpp
dcc32 -cc qgen.dpr
while ((2>1))
do
    ./qgen.exe > q.in
    q < q.in > q1.out
    qs < q.in > q2.out
    diff q1.out q2.out || exit
    echo "OK"
done
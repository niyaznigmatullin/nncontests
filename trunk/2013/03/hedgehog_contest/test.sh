

while true
do
    ./geng.exe > in
    ./g.exe < in > out || exit
    echo "OK"
done
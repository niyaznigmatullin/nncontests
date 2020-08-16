

for ((i=0;i<=1000;i++))
do
	./eg 12 1000 $i > ee.in
	./e < ee.in > e1.out
	./es < ee.in > e2.out
	diff e1.out e2.out || exit
	echo $i
done
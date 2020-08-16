

for ((i=0;i<1000;i++))
do
	./eg 20 20 1000 $i > ee.in
	./e < ee.in > ee1.out
	./es < ee.in > ee2.out
	diff ee1.out ee2.out || exit
	echo $i
done
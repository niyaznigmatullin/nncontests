
for ((i=0;i<1000;i++))
	do
		./b_gen 10 10 10 $i > gg.in || exit
		./b_debug < gg.in > gg.out1 || exit
		./b_slow < gg.in > gg.out2 || exit
		diff gg.out1 gg.out2 || exit
		echo "OK $i"
	done

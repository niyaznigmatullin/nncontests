
const maxp=143;
			modulo=1000000009;
p:array[1..maxp] of integer=(101,
103,
107,
109,
113,
127,
131,
137,
139,
149,
151,
157,
163,
167,
173,
179,
181,
191,
193,
197,
199,
211,
223,
227,
229,
233,
239,
241,
251,
257,
263,
269,
271,
277,
281,
283,
293,
307,
311,
313,
317,
331,
337,
347,
349,
353,
359,
367,
373,
379,
383,
389,
397,
401,
409,
419,
421,
431,
433,
439,
443,
449,
457,
461,
463,
467,
479,
487,
491,
499,
503,
509,
521,
523,
541,
547,
557,
563,
569,
571,
577,
587,
593,
599,
601,
607,
613,
617,
619,
631,
641,
643,
647,
653,
659,
661,
673,
677,
683,
691,
701,
709,
719,
727,
733,
739,
743,
751,
757,
761,
769,
773,
787,
797,
809,
811,
821,
823,
827,
829,
839,
853,
857,
859,
863,
877,
881,
883,
887,
907,
911,
919,
929,
937,
941,
947,
953,
967,
971,
977,
983,
991,
997);

var q,n:integer;
		a:array[0..10001,0..100] of int64;

procedure init;
	begin
	  reset(input,'input.txt');
	  rewrite(output,'output.txt');
	  read(n);
	  close(input);
	end;

procedure solve;
var i,j:integer;
	begin
		if n<3 then
			begin
			  write(0);
			  close(output);
			  halt(0);
			end;
		fillchar(a,sizeof(a),0);	
	  for i:=1 to maxp do
	  	begin
	  	  inc(a[3,p[i] mod 100]);
	  	end;
		for i:=4 to n do
			begin
			  for j:=1 to maxp do
			  	begin
			  	  a[i,p[j] mod 100]:=(a[i,p[j] mod 100]+a[i-1,p[j] div 10]) mod modulo;
			  	end;
			end;
		q:=0;
		for i:=0 to 99 do q:=(q+a[n,i]) mod modulo;
	end;

procedure done;
	begin
		write(q);
		close(output);
		halt(0);
	end;	

begin
	init;
	solve;
	done;
end.
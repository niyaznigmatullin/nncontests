var n,m:integer;begin reset(input,'input.txt');rewrite(output,'output.txt');read(n,m);if(n>27)and(m=1)then inc(n);if(n>29)and(m=3)then inc(n,2);if (n+n+m) mod 3=0 then write(2) else write(1); end.

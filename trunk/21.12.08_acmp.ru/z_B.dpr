program z_B;
{$APPTYPE CONSOLE}
uses SysUtils;

const year:array[1..4] of integer=(366,365,365,365);
			month:array[1..12] of integer=(31,28,31,30,31,30,31,31,30,31,30,31);
			day:array[0..6] of string=('Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday','Monday');

var n:integer;

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
		write(day[n mod 7],', ');
		inc(n);
		i:=1;
		while n>year[i] do
			begin
			  dec(n,year[i]);
			  inc(i);
			end;
		j:=1;
		while (n>month[j]) or ((i=1) and (j=2) and (n>month[j]+1)) do
			begin
			  if (i=1) and (j=2) then dec(n);
			  dec(n,month[j]);
        inc(j);
			end;
		if n<10 then write('0',n) else write(n);
		if j<10 then 
			begin
			  write('.0',j);
			end else write('.',j);
	end;
	
procedure done;
	begin
		close(output);
		halt(0);
	end;	

begin
	init;
	solve;
	done;
end.
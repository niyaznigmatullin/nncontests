var a:array[0..51,0..51] of char;
		u:array[0..51,0..51] of boolean;
		q,n:integer;

procedure rec(x,y:integer);
	begin
	  u[x,y]:=true;
	  if (x>0) then if (a[x-1,y]='.') then if (not u[x-1,y]) then rec(x-1,y) else else inc(q);
	  if (y>0) then if (a[x,y-1]='.') then if (not u[x,y-1]) then rec(x,y-1) else else inc(q);
	  if (x<=n) then if (a[x+1,y]='.') then if (not u[x+1,y]) then rec(x+1,y) else else inc(q);
	  if (y<=n) then if (a[x,y+1]='.') then if (not u[x,y+1]) then rec(x,y+1) else else inc(q);
	end;

procedure init;
var i,j:integer;
	begin
	  reset(input,'input.txt');
	  readln(n);
	  for i:=0 to n+1 do
	  for j:=0 to n+1 do a[i,j]:='#';
	  for i:=1 to n do
	  	begin
	  	  for j:=1 to n do read(a[i,j]);
	  	  readln;
	  	end;
	  close(input);
	end;

procedure solve;
	begin
		fillchar(u,sizeof(u),false);
		q:=0;
		rec(1,1);
		if not u[n,n] then rec(n,n);
		dec(q,4);
	end;
	
procedure done;
	begin
		rewrite(output,'output.txt');
	  write(q*25);
	  close(output);
	  halt(0);
	end;

begin
	init;
	solve;
	done;
end.
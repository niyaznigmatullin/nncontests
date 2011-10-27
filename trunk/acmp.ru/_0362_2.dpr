{$R+,S+,Q+}
const eps=1e-6;

var a,b,c,d:integer;

function check:boolean;
var l,r,m,bb:extended; ok1,ok2:boolean;
	begin
		if (a>c) and (b>d) then
			begin
			  result:=true;
			  exit;
			end;
	  l:=0;
	  r:=a+eps*eps;
	  while r-l>eps*eps*eps do
	  	begin
	  	  m:=(l+r)/2+eps*eps*eps;
	  	  if m>c+eps then
	  	  	begin
	  	  	  r:=m;
	  	  	  continue;
	  	  	end;
	  	  if m>c then bb:=0 else bb:=sqrt(c*c-m*m);
        ok1:=(d*m+bb*c>b*c+eps);
        ok2:=(d*bb+m*c>a*c+eps);
	  	  if ok1 or ok2 then l:=m+eps*eps else begin result:=true; exit; end;
	  	end;
	  result:=false;
	end;

procedure init;
var p:integer;
	begin
	  reset(input,'input.txt');
	  rewrite(output,'output.txt');
	  read(c,d,a,b);
	  if a<b then begin p:=a; a:=b; b:=p; end;
	  if c<d then begin p:=c; c:=d; d:=p; end;
	  close(input);
	end;

procedure solve;
	begin
	  if check then
	  	begin
	  		write('Possible');
	  		close(output);
	  		halt(0);
	  	end;
	end;

procedure done;
	begin
	  write('Impossible');
	  close(output);
	  halt(0);
	end;
		
begin
	init;
	solve;
	done;
end.
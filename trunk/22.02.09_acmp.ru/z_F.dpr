program z_F;

{$APPTYPE CONSOLE}

uses
  SysUtils;

const eps=1e-9;

type
 TPoint=record x,y:extended; end;
 TLine=record a,b,c:extended; end;

var
a,b:array[1..100] of TPoint;
l:array[1..100] of TLine;
n,i,j,ans:integer;
r:extended;
t:Tpoint;


function get_line(k:integer):Tline;
var c:Tline;
begin
  c.a:=b[k].y-a[k].y;
  c.b:=a[k].x-b[k].x;
  c.c:=-a[k].x*c.a-a[k].y*c.b;
  result:=c;
end;

function get_int(l1,l2:Tline):Tpoint;
var x0,y0:extended;
begin
 if l1.b=0 then
   begin
     x0:=-l1.c/l1.a;
     y0:=(-l2.c-x0*l2.a)/l2.b;
     result.x:=x0; result.y:=y0;  exit;
   end;
 if l2.b=0 then
   begin
     x0:=-l2.c/l2.a;;
     y0:=(-l1.c-x0*l1.a)/l1.b;
     result.x:=x0; result.y:=y0; exit;
   end;
 x0:=(l2.c*l1.b-l1.c*l2.b)/(l1.a*l2.b-l2.a*l1.b);
 y0:=(-l1.c-l1.a*x0)/l1.b;
 result.x:=x0; result.y:=y0;

end;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(r,n);
  for i:=1 to n do
    begin
     read(a[i].x);
     read(a[i].y);
     read(b[i].x);
     read(b[i].y);
    end;
  if n=1 then begin write(2); halt(0); end;
  ans:=n*(n+1) div 2+1;
for i:=1 to n do l[i]:=get_line(i);


  for i:=1 to n-1 do
   for j:=i+1 to n do
    begin
      t:=get_int(l[i],l[j]);
      if sqrt(t.x*t.x+t.y*t.y)>r-eps then dec(ans);
    end;
  write(ans);
end.

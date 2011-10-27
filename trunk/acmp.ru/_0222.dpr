program _0222;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TPoint=record
      x,y:extended;
      end;

const eps=1e-7;

var ang:array[0..100001] of extended;
    u:array[0..100001] of boolean;
    q,n:integer;
    p0:TPoint;

function norm(x:extended):extended;
  begin
    while x>2*pi+eps do x:=x-2*pi;
    while x<-eps do x:=x+2*pi;
    result:=x;
  end;

Procedure Sort(L,R:Integer);
Var
  I,J:Integer;
  X,Y:extended;  a,b:boolean;
Begin
  I:=L;
  J:=R;
  X:=ang[(L+R) Div 2];
  a:=u[(l+r) div 2];
  Repeat
    While (ang[I]-X<-eps) or ((abs(ang[i]-x)<=eps) and (u[i]>a)) Do Inc(I);
    While (X-ang[J]<-eps) or ((abs(x-ang[j])<=eps) and (a>u[j])) Do Dec(J);
    If I<=J Then Begin
      Y:=ang[I];
      ang[I]:=ang[J];
      ang[J]:=Y;
      b:=u[i];
      u[i]:=u[j];
      u[j]:=b;
      Inc(I);
      Dec(J);
    End;
  Until I>J;
  If L<J Then
    Sort(L,J);
  If I<R Then
    Sort(I,R);
End;

procedure no;
  begin
    write('NO');
    close(output);
    halt(0);
  end;

procedure yes;
  begin
    write('YES');
    close(output);
    halt(0);
  end;

procedure init;
var i:integer; x,y,r,angle,diff:extended;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    read(p0.x,p0.y);
    for i:=1 to n do
      begin
        read(x,y,r);
        x:=x-p0.x;
        y:=y-p0.y;
        angle:=arctan2(y,x);
        diff:=arcsin(r/sqrt(sqr(x)+sqr(y)));
        ang[i+i-1]:=norm(angle-diff);
        ang[i+i]:=norm(angle+diff);
        u[i+i-1]:=true;
        u[i+i]:=false;
      end;
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    q:=0;
    for i:=1 to n do if ang[i+i-1]-ang[i+i]>eps then inc(q);
    sort(1,n+n);
    if q=0 then if (ang[1]>eps) and (ang[n+n]-2*pi<-eps) then no;
    for i:=1 to n+n do
      begin
        if u[i] then inc(q) else dec(q);
        if (q=0) and (i<n+n) then no;
      end;
    yes;
  end;

begin
  init;
  solve;
end.

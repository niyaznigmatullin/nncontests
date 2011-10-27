program _0155;
{$APPTYPE CONSOLE}
{$O-}
uses
  SysUtils;

const eps=0.01;

var c:array[0..10] of extended;
    u:array[0..10] of boolean;
    n:integer;
    c0:extended;

procedure rec(k:integer);
var i,j:integer; c1,c2:extended;
  begin
    for i:=1 to n do
    if abs(c[i]-c0)<=eps then
      begin
        write('YES');
        close(output);
        halt(0);
      end;
    if k=1 then exit;
    for i:=1 to n-1 do
    for j:=i+1 to n do
    if (not u[i]) and (not u[j]) then
      begin
        c1:=c[i];
        c2:=c[j];
        u[j]:=true;
        c[i]:=c1+c2;
        rec(k-1);
        c[i]:=((c1*c2)/(c1+c2));
        rec(k-1);
        c[i]:=c1;
        c[j]:=c2;
        u[j]:=false;
      end;
                    {
    for i:=1 to n do
    if not u[i] then
      begin
        u[i]:=true;
        rec(cc+c[i],k+1);
        rec((cc*c[i])/(cc+c[i]),k+1);
        u[i]:=false;
      end;           }
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,c0);
    for i:=1 to n do read(c[i]);
    close(input);
  end;

procedure solve;
  begin
    rec(n);
  end;

procedure done;
  begin
    write('NO');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,b,c:array[0..30] of integer;
    n,q:integer;
    d,e,f:array[0..30,0..30] of boolean;

procedure swap(var x,y:integer);
var p:integer;
  begin
    p:=x;
    x:=y;
    y:=p;
  end;

procedure check;
var i,j:integer;
  begin
    for i:=1 to n do
    for j:=i+1 to n do if (f[c[j]][c[i]]) then exit;
    inc(q);
  end;

procedure rec(x:integer);
var i:integer;
  begin
    if (x=n) then
      begin
        check;
      end else
      begin
        for i:=x+1 to n do if (not f[c[x+1],c[i]]) then
          begin
            swap(c[x+1],c[i]);
            rec(x+1);
            swap(c[x+1],c[i]);
          end;
      end;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'genetic.in');
    rewrite(output,'genetic.out');
    readln(n);
    for i:=1 to n do
      begin
        read(a[i]);
      end;
    for i:=1 to n do read(b[i]);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    q:=0;
    fillchar(d,sizeof(d),false);
    fillchar(e,sizeof(e),false);
    for i:=1 to n do
    for j:=i+1 to n do
      begin
        d[a[i],a[j]]:=true;
      end;
    for i:=1 to n do
    for j:=i+1 to n do
      begin
        e[b[i],b[j]]:=true;
      end;
    for i:=1 to n do
    for j:=1 to n do
      begin
        f[i,j]:=e[i,j] and d[i,j];
      end;
    c:=a;
    rec(0);
  end;

procedure done;
  begin
    writeln(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

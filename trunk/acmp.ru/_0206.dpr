program _0206;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,e,m,kol,k,inf:integer;
    w1,w2,v,u,d:array[0..100001] of integer;

procedure init;
var i,x,y,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,e);
    read(m);
    for i:=1 to m do
      begin
        read(k);
        read(x,y);
        for j:=2 to k do
          begin
            inc(kol);
            u[kol]:=x;
            w1[kol]:=y;
            read(x,y);
            v[kol]:=x;
            w2[kol]:=y;
          end;
      end;
    close(input);
  end;


procedure solve;
var i,j:integer;
  begin
    inf:=maxint div 2;
    for i:=1 to n do d[i]:=inf;
    d[1]:=0;
    for i:=1 to n-1 do
    for j:=1 to kol do
      if d[u[j]]<=w1[j] then if d[v[j]]>w2[j] then d[v[j]]:=w2[j];
  end;

procedure done;
  begin
    if d[e]=inf then write(-1) else write(d[e]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
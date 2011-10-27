program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var k,n,m:integer;
    s:array[0..50,0..50] of char;
    ss:array[0..50] of String;

function check(x,y,w:integer):boolean;
var i,j,t,len:integer;
  begin
    i := x;
    j := y;
    len := length(ss[w]);
    t := 1;
    while (i<=n) and (j<=m) and (t<=len) do
      begin
        if (s[i][j] = ss[w][t]) then
          begin
            inc(i);
            inc(t);
          end else break;
      end;
    if (t > len) then
      begin
        result:=true;
        exit;
      end;
    i := x;
    j := y;
    len := length(ss[w]);
    t := 1;
    while (i<=n) and (j<=m) and (t<=len) do
      begin
        if (s[i][j] = ss[w][t]) then
          begin
            inc(j);
            inc(t);
          end else break;
      end;
    if (t > len) then
      begin
        result:=true;
        exit;
      end;
    result:=false;
  end;

procedure swap(var x,y:integer);
var p:integer;
  begin
    p := x;
    x := y;
    y := p;
  end;

procedure init;
var i,j:integer;
  begin
    reset(input,'words.in');
    rewrite(output,'words.out');
    readln(n,m);
    for i:=1 to n do
      begin
        for j:=1 to m do read(s[i][j]);
        readln;
      end;
    readln(k);
    for i:=1 to k do readln(ss[i]);
    close(input);
  end;

procedure solve;
var t,i,j:integer; found:boolean;
  begin
    for t:=1 to k do
      begin
        found := false;
        for i:=1 to n do if (not found) then
        for j:=1 to m do if (not found) then
          begin
            found := found or (check(i,j,t));
          end;
        if (found) then writeln('YES') else writeln('NO');
      end;
    close(output);
  end;

begin
  init;
  solve;
end.

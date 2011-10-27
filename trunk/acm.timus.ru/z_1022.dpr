program z_1022;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var u:array[0..101] of boolean;
    a:array[0..101,0..101] of boolean;
    n,kol:integer;
    w:array[0..101] of integer;

procedure dfs(x:integer);
var i:integer;
  begin
    u[x]:=true;
    for i:=1 to n do if (not u[i]) and (a[x,i]) then dfs(i);
    inc(kol);
    w[kol]:=x;
  end;

procedure init;
var i,x:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(x);
        while x<>0 do
          begin
            a[i,x]:=true;
            read(x);
          end;
      end;
  end;

procedure solve;
var i:integer;
  begin
    for i:=1 to n do if (not u[i]) then dfs(i);
  end;

procedure done;
var i:integer;
  begin
    for i:=n downto 1 do write(w[i],' ');
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
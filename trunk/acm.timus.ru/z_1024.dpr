program z_1024;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var p:array[0..1001] of integer;
    u:array[0..1001] of boolean;
    q,n,k:integer;

procedure init;
var i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p[i]);
  end;

function gcd(x,y:int64):int64;
  begin
    while x*y<>0 do
      begin
        if (x>y) then x:=x mod y else y:=y mod x;
      end;
    result:=x+y;
  end;

function lcm(x,y:int64):int64;
  begin
    result:=x div gcd(x,y)*y;
  end;

procedure solve;
var i,v:integer;
  begin
    fillchar(u,sizeof(u),false);
    q:=1;
    for i:=1 to n do if not u[i] then
      begin
        k:=0;
        v:=i;
        while not u[v] do
          begin
            u[v]:=true;
            v:=p[v];
            inc(k);
          end;
        q:=lcm(q,k);
      end;
  end;

procedure done;
  begin
    write(q);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

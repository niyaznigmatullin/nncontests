program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,n1,n2,v:integer;
    a:array[0..30001] of integer;
    u:array[0..30001] of boolean;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    read(n1,n2);
    for i:=2 to n do read(a[i]);
    close(input);
  end;

procedure solve;
  begin
    fillchar(u,sizeof(u),false);
    v:=n1;
    u[1]:=true;
    while v<>1 do
      begin
        u[v]:=true;
        v:=a[v];
      end;
    v:=n2;
    while not u[v] do
      begin
        v:=a[v];
      end;
  end;

procedure done;
  begin
    write(v);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
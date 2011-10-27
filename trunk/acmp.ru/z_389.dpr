program z_389;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..100000] of integer;
    m,n:integer;

procedure swap(x,y:integer);
var p:integer;
  begin
    p:=a[x];
    a[x]:=a[y];
    a[y]:=p;
  end;

function check:boolean;
var i,d:integer;
  begin
    result:=true;
    a[0]:=a[1 shl n];
    a[(1 shl n)+1]:=a[1];
    for i:=1 to 1 shl n do
      begin
        d:=a[i] xor a[i-1];
        while not odd(d) do d:=d shr 1;
        if d>1 then
          begin
            result:=false;
            exit;
          end;
        d:=a[i] xor a[i+1];
        while not odd(d) do d:=d shr 1;
        if d>1 then
          begin
            result:=false;
            exit;
          end;
      end;
  end;

procedure init;
var i,x,y:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to 1 shl n do read(a[i]);
    read(m);
    for i:=1 to m do
      begin
        read(x,y);
        swap(x,y);
        if check then writeln('Yes') else writeln('No');
      end;
    close(input);
  end;

procedure done;
  begin
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.

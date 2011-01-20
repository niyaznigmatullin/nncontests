program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..101] of integer;
    n:integer;

procedure init;
var i,x,y,z:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(x,y,z);
        a[i]:=x*3600+y*60+z;
      end;
    close(input);
  end;

procedure solve;
var i,j,p:integer;
  begin
    for i:=1 to n-1 do
    for j:=i+1 to n do
      if a[i]>a[j] then
        begin
          p:=a[i];
          a[i]:=a[j];
          a[j]:=p;
        end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do
      begin
        write(a[i] div 3600);
        a[i]:=a[i] mod 3600;
        write(' ',a[i] div 60);
        a[i]:=a[i] mod 60;
        writeln(' ',a[i]);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
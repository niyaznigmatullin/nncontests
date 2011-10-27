program z_1581;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    a:array[0..1001] of integer;

procedure init;
var i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(a[i]);
      end;
  end;

procedure done;
var i,j:integer;
  begin
    i:=1;
    while i<=n do
      begin
        j:=1;
        while (i<n) and (a[i+1]=a[i]) do begin inc(i); inc(j); end;
        write(j,' ',a[i],' ');
        inc(i);
      end;
  end;

begin
  init;
  done;
end.
 
program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..10001] of int64;
    n:integer;
    ans:int64;

procedure sort(l,r:integer);
var i,j,x,y:int64;
  begin
    i:=l;
    j:=r;
    x:=a[random(r-l+1)+l];
    repeat
      while x>a[i] do inc(i);
      while x<a[j] do dec(j);
      if (i<=j) then
        begin
          y:=a[i];
          a[i]:=a[j];
          a[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if (i<r) then sort(i,r);
    if (l<j) then sort(l,j);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    sort(1,n);
    ans:=0;
    i:=1;
    while (a[i]<=ans+1) and (i<=n) do
      begin
        inc(ans,a[i]);
        inc(i);
      end;
  end;

procedure done;
  begin
    write(ans+1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

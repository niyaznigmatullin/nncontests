program _0353;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:INteger;
    a:array[0..1000] of integer;

procedure sort(l,r:integer);
var x,y,i,j:integer;
  begin
    x:=a[random(r-l+1)+l];
    i:=l;
    j:=r;
    repeat
      while x>a[i] do inc(i);
      while x<a[j] do dec(j);
      if i<=j then
        begin
          y:=a[i]; a[i]:=a[j]; a[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if l<j then sort(l,j);
    if i<r then sort(i,r);
  end;

function is_triangle(x:integer):boolean;
  begin
    result:=a[x-2]+a[x-1]>a[x];
  end;

function area(x:integer):extended;
var p:extended;
  begin
    p:=(a[x]+a[x-1]+a[x-2])/2;
    result:=sqrt(p*(p-a[x])*(p-a[x-1])*(p-a[x-2]));
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
    i:=n;
    sort(1,n);
    while i>2 do
      begin
        if is_triangle(i) then
          begin
            write(area(i):0:3);
            close(output);
            halt(0);
          end;
        dec(i);
      end;
  end;

procedure done;
  begin
    write(0);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
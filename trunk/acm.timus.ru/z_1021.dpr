program z_1021;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,b:array[0..50001] of integer;
    n,m:integer;

procedure yes;
  begin
    writeln('YES');
    halt(0);
  end;

procedure sort(l,r:integer);
var i,j,x,y:integer;
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
    if (l<j) then sort(l,j);
    if (i<r) then sort(i,r);
  end;

procedure init;
var i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    read(m);
    for i:=1 to m do read(b[i]);
  end;

procedure solve;
var i,l,r,x,mid:integer;
  begin
    sort(1,n);
    for i:=1 to m do
      begin
        x:=10000-b[i];
        l:=1;
        r:=n;
        while l<=r do
          begin
            mid:=(l+r) div 2;
            if (a[mid]=x) then yes else if (a[mid]>x) then r:=mid-1 else l:=mid+1;
          end;
      end;
  end;

procedure done;
  begin
    write('NO');
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

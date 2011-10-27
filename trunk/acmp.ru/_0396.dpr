program _0396;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type mass=array[0..100001] of integer;

var w,a,b,c,d:mass;
    n,m:integer;

procedure sort(var a:mass; l,r:integer);
  procedure qsort(l,r:integer);
  var x,y,i,j:integer;
    begin
      x:=a[random(r-l+1)+l];
      i:=l;
      j:=r;
      repeat
        while a[i]<x do inc(i);
        while a[j]>x do dec(j);
        if i<=j then
          begin
            y:=a[i]; a[i]:=a[j]; a[j]:=y;
            inc(i);
            dec(j);
          end;
      until i>j;
      if j>l then qsort(l,j);
      if i<r then qsort(i,r);
    end;

  begin
    qsort(l,r);
  end;

procedure sort1(l,r:integer);
  var x,y,i,j:integer;
    begin
      x:=c[random(r-l+1)+l];
      i:=l;
      j:=r;
      repeat
        while c[i]<x do inc(i);
        while c[j]>x do dec(j);
        if i<=j then
          begin
            y:=c[i]; c[i]:=c[j]; c[j]:=y;
            y:=d[i]; d[i]:=d[j]; d[j]:=y;
            inc(i);
            dec(j);
          end;
      until i>j;
      if j>l then sort1(l,j);
      if i<r then sort1(i,r);
    end;

procedure init;
var i,p:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    for i:=1 to n do
      begin
        read(a[i],b[i]);
        if a[i]>b[i] then
          begin
            p:=a[i];
            a[i]:=b[i];
            b[i]:=p;
          end;
      end;
    for i:=1 to m do read(c[i]);
    close(input);
  end;

procedure solve;
var i,j,q,k:integer;
  begin
    sort(a,1,n);
    sort(b,1,n);
    for i:=1 to m do d[i]:=i;
    sort1(1,m);
    i:=1;
    j:=1;
    q:=0;
    for k:=1 to m do
      begin
        while (a[i]<=c[k]) and (i<=n) do
          begin
            inc(i);
            inc(q);
          end;
        while (b[j]<c[k]) and (j<=n) do
          begin
            inc(j);
            dec(q);
          end;
        w[d[k]]:=q;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to m do
      begin
        write(w[i],' ');
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

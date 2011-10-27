program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var x,y,a:array[0..2000] of integer;
    n : integer;

procedure sort1(l,r:integer);
var i,j,xx,yy:integer;
  begin
    if (l >= r) then exit;
    i := l;
    j := r;
    xx := a[(l + r) shr 1];
    repeat
      while (xx > a[i]) do inc(i);
      while (xx < a[j]) do dec(j);
      if (i <= j) then
        begin
          yy := a[i];
          a[i] := a[j];
          a[j] := yy;
          inc(i);
          dec(j);
        end;
    until i > j;
    sort1(i,r);
    sort1(l,j);
  end;

procedure sort2(l,r:integer);
var i,j,xx,yy:integer;
  begin
    if (l >= r) then exit;
    i := l;
    j := r;
    xx := y[(l + r) shr 1];
    repeat
      while (xx > y[i]) do inc(i);
      while (xx < y[j]) do dec(j);
      if (i <= j) then
        begin
          yy := y[i];
          y[i] := y[j];
          y[j] := yy;
          inc(i);
          dec(j);
        end;
    until i > j;
    sort2(i,r);
    sort2(l,j);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(x[i],y[i]);
      end;
    close(input);
  end;

procedure solve;
var i,kol,ans, kol2,j:integer;
  begin
    kol := 0;
    for i:=1 to n do
      begin
        inc(kol);
        a[kol] := x[i];
        inc(kol);
        a[kol] := y[i];
      end;
    inc(kol);
    a[kol]:= 0;
    inc(kol);
    a[kol] := 1000000000;
    sort1(1,kol);
    ans := 0;
    kol2 := 0;
    i := 1;
    while (i <= kol) do
      begin
        j := i + 1;
        while (j < kol) and (a[j] = a[j + 1]) do inc(j, 2);
        inc(kol2, 2);
        a[kol2 - 1] := a[i];
        a[kol2] := a[j];
        i := j + 1;
      end;
    for i:=1 to kol2-1 do
      begin
        if (odd(i)) then ans := Math.max(ans, a[i + 1] - a[i]);
      end;
    write(ans);
    close(output);
  end;

begin
  init;
  solve;
end.

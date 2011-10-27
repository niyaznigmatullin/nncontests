program _0377;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    a,b:array[0..20000] of int64;
    q:int64;

procedure sort(l,r: integer);
  var
    i,j,x,y: integer;
  begin
    i:=l; j:=r; x:=a[(l+r) div 2]; { x := a[(r+l) div 2]; - для выбора среднего элемента }
    repeat
      while a[i]<x do i:=i+1; { a[i] > x  - сортировка по убыванию}
      while x<a[j] do j:=j-1; { x > a[j]  - сортировка по убыванию}
      if i<=j then begin
          y:=a[i]; a[i]:=a[j]; a[j]:=y;
          y:=b[i]; b[i]:=b[j]; b[j]:=y;
        i:=i+1; j:=j-1;
      end;
    until i>j;
    if l<j then sort(l,j);
    if i<r then sort(i,r);
  end; {sort}

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do begin read(a[i],b[i]); if a[i]>b[i] then begin b[i]:=a[i] xor b[i]; a[i]:=a[i] xor b[i]; b[i]:=a[i] xor b[i]; end; end;
    close(input);
  end;

procedure solve;
var i,j:integer; maxy:integer;
  begin
    i:=1;
    q:=0;
    sort(1,n);
    while i<=n do
      begin
        j:=i;
        maxy:=b[i];
        while (j<n) and (maxy>=a[j+1]) do
          begin
            inc(j);
            if maxy<b[j] then maxy:=b[j];
          end;
        inc(q,abs(maxy-a[i]));
        i:=j+1;
      end;
  end;

procedure done;
  begin
    write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

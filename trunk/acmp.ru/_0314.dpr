program _0314;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,k:integer;
    s:array[0..10001] of string;
    ss:string;

procedure sort(l,r: integer);
  var
    i,j: integer; x,y:string;
  begin
    i:=l; j:=r; x:=s[random(r-l+1)+l]; { x := a[(r+l) div 2]; - для выбора среднего элемента }
    repeat
      while s[i]<x do i:=i+1; { a[i] > x  - сортировка по убыванию}
      while x<s[j] do j:=j-1; { x > a[j]  - сортировка по убыванию}
      if i<=j then begin
        if s[i] > s[j] then begin  { это условие можно убрать } {a[i] < a[j] при сортировке по убыванию}
          y:=s[i]; s[i]:=s[j]; s[j]:=y;
        end;
        i:=i+1; j:=j-1;
      end;
    until i>j;
    if l<j then sort(l,j);
    if i<r then sort(i,r);
  end; {sort}

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k);
    close(input);
    ss:=inttostr(k);
  end;

procedure solve;
var i:integer;
  begin
    for i:=1 to n do s[i]:=inttostr(i);
    sort(1,n);
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do if s[i]=ss then
      begin
        write(i);
        break;
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

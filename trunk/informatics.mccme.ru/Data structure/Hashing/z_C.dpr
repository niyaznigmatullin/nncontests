program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var kolh:integer=0;
    h:array[0..100001] of integer;


procedure swap(x,y:integer);
var p:integer;
  begin
    p:=h[x];
    h[x]:=h[y];
    h[y]:=p;
  end;

function min_heap:integer;
  begin
    result:=h[1];
  end;

procedure heap_down(x:integer);
var r:integer;
  begin
    while (2*x+1<=kolh) and ((h[x]>h[2*x]) or (h[x]>h[2*x+1])) do
      begin
        if (h[2*x]<h[2*x+1]) then r:=2*x else r:=2*x+1;
        swap(x,r);
        x:=r;
      end;
    if (2*x=kolh) and (h[x]>h[2*x]) then
      begin
        swap(x,2*x);
      end;
  end;

procedure delete_heap;
  begin
    swap(1,kolh);
    dec(kolh);
    heap_down(1);
  end;

procedure heap_up(x:integer);
  begin
    while (x>1) do if (h[x]<h[x shr 1]) then
      begin
        swap(x,x shr 1);
        x:=x shr 1;
      end else break;
  end;

procedure heap_insert(x:integer);
  begin
    inc(kolh);
    h[kolh]:=x;
    heap_up(kolh);
  end;

var n,x1,x2,x,i:integer;
    ans:int64;

begin
  read(n);
  ans:=0;
  for i:=1 to n do
    begin
      read(x);
      heap_insert(x);
    end;
  readln;
  for i:=1 to n-1 do
    begin
      x1:=min_heap;
      delete_heap;
      x2:=min_heap;
      delete_heap;
      ans:=ans+x1+x2;
      heap_insert(x1+x2);
    end;
  writeln((ans*0.05):0:2);
  readln;
end.

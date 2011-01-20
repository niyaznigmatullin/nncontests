program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type Ptreap=^treap;
      treap=record
        next:PTreap;
        pos:integer;
        end;

var f1,f2:array[-1000000..1000000] of PTreap;
    a,b:array[0..100001] of integer;
    n:integer;
    ans:int64;

procedure quit;
  begin
    close(output);
    halt(0);
  end;

function mergesort(l,r:int64):int64;
var i,j,k,m,e:integer;
  begin
    if (l=r) then
      begin
        result:=0;
        exit;
      end;
    m:=(l+r) div 2;
    result:=mergesort(l,m)+mergesort(m+1,r);
    i:=l;
    j:=m+1;
    k:=0;
    while (i<=m) and (j<=r) do
      begin
        if (a[i]<a[j]) then
          begin
            inc(k);
            b[k]:=a[i];
            inc(i);
          end else
          begin
            inc(result,m-i+1);
            inc(k);
            b[k]:=a[j];
            inc(j);
          end;
      end;
    while (i<=m) do
      begin
        inc(k);
        b[k]:=a[i];
        inc(i);
      end;
    while (j<=r) do
      begin
        inc(k);
        b[k]:=a[j];
        inc(j);
      end;
    for e:=1 to k do
      begin
        a[e+l-1]:=b[e];
      end;
    exit;
  end;

procedure init;
var i,x:integer; v:PTreap;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(x);
        new(v);
        v^.next:=f1[x];
        v^.pos:=i;
        f1[x]:=v;
      end;
    for i:=1 to n do
      begin
        read(x);
        new(v);
        v^.next:=f2[x];
        v^.pos:=i;
        f2[x]:=v;
      end;
    close(input);
  end;

procedure solve;
var i:integer; v1,v2:PTreap;
  begin
    for i:=-1000000 to 1000000 do
      begin
        v1:=f1[i];
        v2:=f2[i];
        while (v1<>nil) and (v2<>nil) do
          begin
            a[v1^.pos]:=v2^.pos;
            v1:=v1^.next;
            v2:=v2^.next;
          end;
        if (v1=nil) xor (v2=nil) then
          begin
            writeln(-1);
            quit;
          end;
      end;
    ans:=mergesort(1,n);
  end;

procedure done;
  begin
    write(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
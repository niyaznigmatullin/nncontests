program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TElem=record
      v,n:integer;
      end;

const INF=1 shl 30;

var a:array[0..262145] of TElem;
    no:TElem;
    k,n:integer;

function maxx(x,y:TElem):TElem;
  begin
    if (x.v>y.v) then result:=x else result:=y;
  end;

function find_max(node,l,r,ll,rr:integer):TElem;
  begin
    if (ll>r) or (rr<l) then result:=no else
    if (ll>=l) and (rr<=r) then result:=a[node] else
      begin
        result:=maxx(find_max(node+node,l,r,ll,(ll+rr) div 2),find_max(node+node+1,l,r,(ll+rr) div 2+1,rr));
      end;
  end;

var nn,i,m,x,y:integer;
    ans:TElem;

begin
  read(n);
  k:=0;
  nn:=n;
  no.v:=-INF;
  no.n:=0;
  while (nn>0) do begin nn:=nn shr 1; inc(k); end;
  k:=(1 shl k)-1;
  for i:=1 to n do begin read(a[i+k].v); a[i+k].n:=i; end;
  for i:=n+k+1 to k+k+2 do a[i].v:=-INF;
  for i:=k downto 1 do
    begin
      if (a[i+i].v>a[i+i+1].v) then
        begin
          a[i]:=a[i+i];
        end else a[i]:=a[i+i+1];
    end;
  read(m);
  for i:=1 to m do
    begin
      read(x,y);
      ans:=find_max(1,x,y,1,k+1);
      writeln(ans.v,' ',ans.n);
    end;
  readln;
  readln;
end.

program z_1;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var n,m:int64;
    kol1,kol2:integer;
    w1,e1,w2,e2:array[0..100] of int64;

procedure search1(x:integer);
var l,r,p,mid:int64; i:integer;
  begin
    l:=2;
    r:=n;
    while true do
      begin
        mid:=(l+r) div 2;
        p:=1;
        if power(mid,x)>2000000000000000000 then
          begin
            r:=mid-1;
          end else
          begin
            for i:=1 to x do p:=p*mid;
            if p=n then
              begin
                inc(kol1);
                w1[kol1]:=mid;
                e1[kol1]:=x;
                exit;
              end else
            if p<n then
              begin
                l:=mid+1
              end else r:=mid-1;
          end;
        if l>r then exit;
      end;
  end;

procedure search2(x:integer);
var l,r,p,mid:int64; i:integer;
  begin
    l:=2;
    r:=n;
    while true do
      begin
        mid:=(l+r) div 2;
        p:=1;
        if power(mid,x)>2000000000000000000 then
          begin
            r:=mid-1;
          end else
          begin
            for i:=1 to x do p:=p*mid;
            if p=m then
              begin
                inc(kol2);
                w2[kol2]:=mid;
                e2[kol2]:=x;
                exit;
              end else
            if p<m then
              begin
                l:=mid+1
              end else r:=mid-1;
          end;
        if l>r then exit;
      end;
  end;

function gcd(x,y:int64):int64;
  begin
    while x*y<>0 do
      begin
        if x>y then x:=x mod y else y:=y mod x;
      end;
    result:=x+y;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure solve;
var i,j:integer; g:int64;
  begin
    if m=1 then begin write('0 1'); close(output); halt(0); end;
    for i:=1 to 63 do search1(i);
    for i:=1 to 63 do search2(i);
    for i:=1 to kol1 do
    for j:=1 to kol2 do if w1[i]=w2[j] then
      begin
        g:=gcd(e1[i],e2[j]);
        write(e2[j] div g,' ',e1[i] div g);
        close(output);
        halt(0);
      end;
  end;

procedure done;
  begin
    write('irrational');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..1 shl 19] of int64;
    kol,n,m:integer;

function min(x,y:int64):int64;
  begin
    if x<y then result:=x else result:=y;
  end;

procedure update(t,l,r,lp,rp,x:integer);
  begin
    if (rp<l) or (lp>r) or (a[t]>x) then exit else
    if (lp>=l) and (rp<=r) then
      begin
        a[t]:=x;
        exit;
      end else
      begin
        if l<=(lp+rp) div 2 then
          begin
            update(t+t,l,r,lp,(lp+rp) div 2,x);
          end;
        if r>=(lp+rp) div 2+1 then
          begin
            update(t+t+1,l,r,(lp+rp) div 2+1,rp,x);
          end;
      end;
  end;

function minr(t,l,r,lp,rp:integer):int64;
  begin
    result:=int64(maxint)+1;
    if (rp<l) or (lp>r) then exit else
    if (lp>=l) and (rp<=r) then
      begin
        result:=a[t];
        exit;
      end else
      begin
        result:=min(minr(t+t,l,r,lp,(lp+rp) div 2), minr(t+t+1,l,r,(lp+rp) div 2+1,rp));
      end;
  end;

function search(x:integer):int64;
  begin
    while (x>1) do
      begin
        if a[x]>-5000000000000 then
          begin
            result:=a[x];
            exit;
          end;
        x:=x div 2;
      end;
    result:=maxint;
  end;

procedure init;
var i,x,y,q:integer;
  begin
    reset(input,'rmq.in');
    rewrite(output,'rmq.out');
    read(n,m);
    kol:=1;
    while kol<n do kol:=kol*2;
    kol:=kol*2;
    for i:=1 to kol do a[i]:=-5000000000000;
    for i:=1 to m do
      begin
        read(x,y,q);
        update(1,x,y,1,kol div 2,q);
      end;
    close(input);
  end;

procedure solve;
var i:integer; x,y,q:integer; p:int64;
  begin
    for i:=kol div 2 to kol div 2+n-1 do
      begin
        a[i]:=search(i);
      end;
    for i:=kol div 2+n to kol do a[i]:=maxint;
    for i:=kol div 2-1 downto 1 do
      begin
        a[i]:=min(a[i+i],a[i+i+1]);
      end;
    reset(input,'rmq.in');
    read(n,m);
    for i:=1 to m do
      begin
        read(x,y,q);
        p:=minr(1,x,y,1,kol div 2);
        if p<>q then
          begin
            write('inconsistent');
            close(output);
            halt(0);
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    writeln('consistent');
    for i:=kol div 2 to kol div 2+n-1 do write(a[i],' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

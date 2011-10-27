{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

//const fact:array[0..10] of integer=(1,1,2,6,24,120,720,5040,40320,362880,3628800);

var q,j,n,k,mm:integer;
    m:int64;
    a:array[-1..100,-1..100] of integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

function comb(x,y:integer):integer;
  begin
    result:=a[x,y];
  end;

function count_numb(x:integer):integer;
var i,t,w:integer; b:boolean;
  begin
    i:=1;
    result:=1;
    if x and 1<>0 then
      begin
        w:=1;
        b:=false;
      end else
      begin
        w:=0;
        b:=true;
      end;
    for t:=i to j-2 do
      begin
        if (x and (1 shl t)<>0) and b then
          begin
            inc(result,comb(t,w+1));
          end;
        if x and (1 shl t)=0 then b:=true else inc(w);
      end;
    {while (1 shl (i-1)) and x<>0 do dec(i);
    for t:=i+1 to j-1 do
      begin
        inc(result,comb(t-1,k-1));
      end;}
  end;

procedure getnext;
var p,i,w,k:integer;
  begin
    p:=2;
    i:=2;
    w:=0;
    if (m and 1)<>0 then w:=1;
    while (i<j) do
      begin
        if (p and m=0) and ((p shr 1) and m<>0) then
          begin
            inc(m,p);
            dec(m,p shr 1);
            m:=m shr (i-1);
            m:=m shl (i-w);
            for k:=1 to w-1 do
              begin
                m:=m shl 1;
                inc(m);
              end;
            exit;
          end else if (p and m<>0) then inc(w);
        p:=p shl 1;
        inc(i);
      end;
  end;

procedure getcomb;
var i,j:integer;
  begin
    a[0,0]:=1;
    for i:=1 to 30 do
    for j:=0 to i do
      begin
        a[i,j]:=a[i-1,j-1]+a[i-1,j];
      end;
  end;

procedure solve;
var w,i,ww,p,last:integer;
  begin
    getcomb;
    q:=0;
    j:=3;
    while n>q do
      begin
        last:=comb(j-1,2);
        inc(q,last);
        inc(j);
      end;
    dec(q,last);
    mm:=1;
    for i:=1 to j-3-1 do
      begin
        mm:=mm shl 1;
      end;
    mm:=mm*2+1;
    mm:=mm*2+1;
    m:=mm;
    for i:=q+2 to n do getnext;
  end;

procedure done;
  begin
    write(m);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

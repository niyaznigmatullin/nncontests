{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0146_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..3010] of smallint;

const base=10;

var h,h1,d,e,a,b,c:TLong;
    s:string;
    m,p,k,n:integer;
    w:array[0..10] of TLong;

function max(x,y:smallint):smallint;
  begin
    if x>y then result:=x else result:=y;
  end;

function more_long{(cc,bb:TLong; m:integer)}:boolean;
var i:integer;
  begin
    result:=false;
    if e[0]+m>a[0] then
      begin
        result:=true;
        exit;
      end else
    if e[0]+m<a[0] then
      begin
        result:=false;
        exit;
      end else
      begin
        for i:=e[0] downto 1 do
          begin
            if e[i]>a[i+m] then
              begin
                result:=true;
                exit;
              end else
            if e[i]<a[i+m] then
              begin
                result:=false;
                exit;
              end;
          end;
      end;
  end;

procedure add_long{(var cc:TLong; aa,bb:TLong)};
var um,i:integer;
  begin
    um:=0;
    //fillchar(cc,sizeof(cc),0);
    h1[0]:=b[0]+1;
    h1[h1[0]]:=0;
    for i:=1 to h1[0] do
      begin
        h1[i]:=(um+b[i]+b[i]) mod base;
        um:=(um+b[i]+b[i]) div base;
      end;
    if h1[h1[0]]=0 then dec(h1[0]);
  end;

function equal_long{(cc,bb:TLong; m:integer)}:boolean;
var i:integer;
  begin
    result:=true;
    if e[0]+m<>a[0] then
      begin
        result:=false;
        exit;
      end else
      begin
        for i:=1 to e[0] do if e[i]<>a[i+m] then
          begin
            result:=false;
            exit;
          end;
        for i:=1 to m do if a[i]<>0 then
          begin
            result:=false;
            exit;
          end;
      end;
  end;

procedure outt;
var i:integer;
  begin
    for i:=n downto n-k+2 do write(b[i]);
    write(p);
    for i:=1 to n-k do write(0);
    close(output);
    halt(0);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

procedure solve;
var i,um:integer;
  begin
    a[0]:=length(s);
    for i:=length(s) downto 1 do a[a[0]-i+1]:=ord(s[i])-ord('0');
    n:=(length(s)+1) div 2;
    p:=9;
    k:=1;
    w[0,0]:=1;
    w[1,0]:=1;
    w[1,1]:=1;
    w[2,0]:=1;
    w[2,1]:=4;
    w[3,0]:=1;
    w[3,1]:=9;
    w[4,0]:=2;
    w[4,1]:=6;
    w[4,2]:=1;
    w[5,0]:=2;
    w[5,1]:=5;
    w[5,2]:=2;
    w[6,0]:=2;
    w[6,1]:=6;
    w[6,2]:=3;
    w[7,0]:=2;
    w[7,1]:=9;
    w[7,2]:=4;
    w[8,0]:=2;
    w[8,1]:=4;
    w[8,2]:=6;
    w[9,0]:=2;
    w[9,1]:=1;
    w[9,2]:=8;
    b[0]:=1;
    fillchar(e,sizeof(e),0);
    while true do
      begin
        um:=0;
        h[0]:=h1[0]+2;
        h[h[0]]:=0;
        h[h[0]-1]:=0;
        for i:=1 to h[0] do
          begin
            h[i]:=(um+h1[i]*p) mod base;
            um:=(um+h1[i]*p) div base;
          end;
        while (h[0]>1) and (h[h[0]]=0) do dec(h[0]);
        um:=0;
        d[0]:=max(c[0],w[p][0])+1;
        d[d[0]]:=0;
        for i:=1 to d[0] do
          begin
            d[i]:=(um+c[i]+w[p][i]) mod base;
            um:=(um+c[i]+w[p][i]) div base;
          end;
        if d[d[0]]=0 then dec(d[0]);
        um:=0;
    e[0]:=max(d[0],h[0])+1;
    e[e[0]]:=0;
    for i:=1 to e[0] do
      begin
        e[i]:=(um+d[i]+h[i]) mod base;
        um:=(um+d[i]+h[i]) div base;
      end;
    if e[e[0]]=0 then dec(e[0]);
        m:=n-k+n-k;
        if more_long{(e,a,m)} then
          begin
            dec(p);
            continue;
          end else
        if equal_long{(e,a,m)}  then
          begin
            outt;
          end else
          begin
            if k=n then
              begin
                b[1]:=p;
                outt;
              end else
              begin
                for i:=e[0]+2 downto 3 do c[i]:=e[i-2];
                c[1]:=0;
                c[2]:=0;
                c[0]:=e[0]+2;
                for i:=b[0]+2 downto 3 do b[i]:=b[i-1];
                b[2]:=p;
                inc(b[0]);
                p:=9;
                inc(k);
                add_long{(h1,b,b)};
              end;
          end;
      end;
  end;

begin
  init;
  solve;
end.

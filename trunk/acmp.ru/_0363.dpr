{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q-,R-,S-,T-,U-,V+,W-,X+,Y+,Z1}
program _0363;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..3000] of int64;

const base=100000000;
      kk=8;

var s1,s2:string;
    a,b,c:TLong;

procedure long_mul(var c:TLong; a,b:TLong);
var p:int64; k,i,j:integer;
  begin
    fillchar(c,sizeof(c),0);
    for i:=1 to a[0] do
      begin
        for j:=1 to b[0] do
          begin
            k:=i+j-1;
            p:=c[k]+a[i]*b[j];
            c[k]:=p mod base;
            c[k+1]:=c[k+1]+p div base;
            repeat
              inc(k);
              c[k+1]:=c[k+1]+c[k] div base;
              c[k]:=c[k] mod base;
            until c[k+1]=0;
          end;
      end;
    c[0]:=a[0]+b[0]+2;
    while (c[c[0]]=0) and (c[0]>1) do dec(c[0]);
    while c[c[0]]>=base do
      begin
        c[c[0]+1]:=c[c[0]] div base;
        c[c[0]]:=c[c[0]] mod base;
        inc(c[0]);
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s1);
    readln(s2);
    s1:=trim(s1);
    s2:=trim(s2);
    close(input);
  end;

procedure solve;
var j,i,m:integer;
  begin
    m:=length(s1);
    a[0]:=m div kk;
    if m mod kk<>0 then inc(a[0]);
    j:=1;
    for i:=2 to a[0] do
      begin
        a[j]:=strtoint(copy(s1,length(s1)-kk+1,kk));
        setlength(s1,length(s1)-kk);
        inc(j);
      end;
    a[a[0]]:=strtoint(s1);
    m:=length(s2);
    b[0]:=m div kk;
    if m mod kk<>0 then inc(b[0]);
    j:=1;
    for i:=2 to b[0] do
      begin
        b[j]:=strtoint(copy(s2,length(s2)-kk+1,kk));
        setlength(s2,length(s2)-kk);
        inc(j);
      end;
    b[b[0]]:=strtoint(s2);
    long_mul(c,a,b);
  end;

procedure done;
var i:integer;
  begin
    write(c[c[0]]);
    for i:=c[0]-1 downto 1 do
      begin
    //    if c[i]<100000000 then write(0);
        if c[i]<10000000 then write(0);
        if c[i]<1000000 then write(0);
        if c[i]<100000 then write(0);
        if c[i]<10000 then write(0);
        if c[i]<1000 then write(0);
        if c[i]<100 then write(0);
        if c[i]<10 then write(0);
        write(c[i]);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

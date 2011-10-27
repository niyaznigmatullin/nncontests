program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..500] of longint;

var k,n:longint;
    a:Tlong;
procedure long_fact(var a:TLong; const v:longint);
var i,um,j,p:longint;
  begin
    for i:=1 to v do
      begin
        um:=0;
        for j:=1 to a[0] do
          begin
            p:=a[j]*i+um;
            a[j]:=p mod 10;
            um:=p div 10;
          end;
        while um>0 do
          begin
            inc(a[0]);
            a[a[0]]:=um mod 10;
            um:=um div 10;
          end;
      end;
  end;

procedure long_div(var a:TLong; const v:longint);
var b:TLong; i,um:longint;
  begin
    fillchar(b,sizeof(b),0);
    b[0]:=a[0];
    um:=0;
    for i:=a[0] downto 1 do
      begin
        b[i]:=(um*10+a[i]) div v;
        um:=(a[i]+um*10) mod v;
      end;
    while b[b[0]]=0 do dec(b[0]);
    a:=b;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k);
    close(input);
  end;

procedure solve;
var i:longint;
  begin
    n:=n div 5;
    a[0]:=1;
    a[1]:=1;
    long_fact(a,n+k);
    for i:=2 to n do
      begin
        long_div(a,i);
      end;
    for i:=2 to k do
      begin
        long_div(a,i);
      end;
  end;

procedure done;
var i:longint;
  begin
    for i:=a[0] downto 1 do write(a[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end. 

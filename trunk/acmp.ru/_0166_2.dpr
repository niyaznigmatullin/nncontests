{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+  ,T-,U-,V+,W-,X+,Y+,Z1}
program _0166_2;
{$APPTYPE CONSOLE}
uses
  SysUtils;
const kr:array[0..10] of int64=(0,0,0,5,5,9,10,10,14,15,18);
      r:array[0..4] of int64=(0,1,2,5,6);

var k3,k2,k1,k0:int64;
    nn,kk,n,k:integer;

function make(x:int64):int64;
var d:int64;
begin
  if x<11 then result:=kr[x] else
    begin
      d:=x div 5;
      result:=9*d+r[x mod 5];
    end;
end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(k,n);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    k1:=k;
    k3:=0;
    k2:=0;
    for i:=1 to n-1 do
      begin
        k0:=make(k3+k2+k1);
        k3:=k2;
        k2:=k1;
        k1:=k0;
      end;
  end;

procedure done;
  begin
    write(int64(k1)+k2+k3);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.


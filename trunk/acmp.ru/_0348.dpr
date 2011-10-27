{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0348;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TPoint=record
      x,y:int64;
      end;

var p1,p2,p3,p4:TPoint;
    res:boolean;

procedure swap(var x,y:TPoint);
var p:TPoint;
  begin
    p:=x;
    x:=y;
    y:=p;
  end;

function int_otr(s1,s2,s3,s4:TPoint):boolean;
var b1,b2,b3,b4:shortint;
  begin
    b1:=sign((s3.x-s1.x)*(s2.y-s3.y)-(s3.y-s1.y)*(s2.x-s3.x));
    b2:=sign((s4.x-s1.x)*(s2.y-s4.y)-(s4.y-s1.y)*(s2.x-s4.x));
    b3:=sign((s2.x-s3.x)*(s4.y-s2.y)-(s4.x-s2.x)*(s2.y-s3.y));
    b4:=sign((s1.x-s3.x)*(s4.y-s1.y)-(s4.x-s1.x)*(s1.y-s3.y));
    if (b1=0) and (b2=0) and (b3=0) and (b4=0) then
      begin
        result:=(min(s2.x,s4.x)>=max(s1.x,s3.x)) and (min(max(s1.y,s2.y),max(s3.y,s4.y))>=max(min(s1.y,s2.y),min(s3.y,s4.y)));
      end else
    if (b1<>b2) and (b3<>b4) then
        result:=true else result:=false;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(p1.x,p1.y,p2.x,p2.y,p3.x,p3.y,p4.x,p4.y);
    close(input);
  end;

procedure solve;
  begin
    if (p1.x>p2.x) then swap(p1,p2);
    if (p3.x>p4.x) then swap(p3,p4);
    res:=int_otr(p1,p2,p3,p4);
  end;

procedure done;
  begin
    if res then write('Yes') else write('No');
    close(output);
  end;

begin
  init;
  solve;
  done;
end.

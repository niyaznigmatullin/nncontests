{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0252;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const eps=0.00000005;
var n:longint;
    s:array[0..1001] of string;
    a:array[0..1001] of extended;

procedure init;
var i:longint; c1,c:char; x:int64;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do
      begin
        x:=0;
        c:=#3;
        while c<>' ' do
          begin
            read(c);
            s[i]:=s[i]+c;
            if c in ['0'..'9'] then x:=x*10+ord(c)-ord('0');
          end;
        read(c);
        s[i]:=s[i]+c;
        if c in ['m','M','G','k'] then
          begin
            read(c1);
            s[i]:=s[i]+c1;
            case c1 of
              'g':a[i]:=x/1000;
              'p':a[i]:=x*16.380;
              't':a[i]:=x*1000;
              end;
            case c of
              'm':a[i]:=a[i]/1000;
              'k':a[i]:=a[i]*1000;
              'M':a[i]:=a[i]*1000000;
              'G':a[i]:=a[i]*1000000000;
              end;
          end else
          begin
            case c of
              'g':a[i]:=x/1000;
              'p':a[i]:=x*16.380;
              't':a[i]:=x*1000;
              end;
          end;
        readln;
      end;
  end;

procedure solve;
var i,j:longint; ss:string; p:extended;b:array[0..1001] of extended;
  begin
    for i:=1 to n do b[i]:=i;
    for i:=1 to n-1 do
    for j:=i+1 to n do
      if a[i]-a[j]>eps then
        begin
          p:=b[i];
          b[i]:=b[j];
          b[j]:=p;
          p:=a[i];
          a[i]:=a[j];
          a[j]:=p;
          ss:=s[i];
          s[i]:=s[j];
          s[j]:=ss;
        end else if abs(a[i]-a[j])<=eps then
        if b[i]-b[j]>eps then
        begin
          p:=b[i];
          b[i]:=b[j];
          b[j]:=p;
          p:=a[i];
          a[i]:=a[j];
          a[j]:=p;
          ss:=s[i];
          s[i]:=s[j];
          s[j]:=ss;
        end;
  end;

procedure done;
var i:longint;
  begin
    for i:=1 to n do writeln(s[i]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

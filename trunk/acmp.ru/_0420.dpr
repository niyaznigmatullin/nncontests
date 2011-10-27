{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0420;

{$APPTYPE CONSOLE}

uses
  SysUtils;

var s:string;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    s:=trim(s);
    close(input);
  end;

procedure no;
  begin
    write('NO');
    close(output);
    halt(0);
  end;

function next_chem(x:integer; var last:integer):string;
var tmp:string;
  begin
    tmp:='';
    result:='';
    while (x<=length(s)) and (not (s[x] in ['A'..'Z'])) do
      begin
        tmp:=tmp+s[x];
        inc(x);
      end;
    if (tmp='0') or (tmp='1') or ((length(tmp)>0) and (tmp[1]='0')) then no;
    if (x>length(s)) then
      begin
        result:='nil';
        last:=x;
        exit;
      end else result:=s[x];
    if (x<length(s)) and (s[x+1] in ['a'..'z']) then
      begin
        result:=result+s[x+1];
        last:=x+2;
      end else last:=x+1;
    if (x>length(s)) and (result='') then result:='nil';
  end;

procedure solve;
var i:integer; r,last:string; e:integer;
  begin
    if not (s[1] in ['A'..'Z']) then
      begin
        no;
      end;
    i:=1;
    last:='akdfs;ka;flka;lf';
    while (i<=length(s)) do
      begin
        r:=next_chem(i,e);
        i:=e;
        if (r=last) then no;
        last:=r;
      end;
  end;

procedure done;
  begin
    write('YES');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 

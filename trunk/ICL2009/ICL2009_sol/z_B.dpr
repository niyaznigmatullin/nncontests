program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s1,s2:string;

function check(x,y:integer):boolean;
  begin
    if (x=0) and (y=0) then
      begin
        result:=true;
        exit;
      end else
    if (x=0) then
      begin
        result:=false;
        exit;
      end else
    if y<0 then
      begin
        result:=false;
        exit;
      end else
    if s1[x]='*' then
      begin
        if check(x-1,y) then result:=true else result:=check(x,y-1);
        exit;
      end else
    if y=0 then
      begin
        result:=false;
        exit;
      end;
    if (s1[x]=s2[y]) or (s1[x]='?') then
      begin
        result:=check(x-1,y-1);
        exit;
      end else result:=false;
  end;

procedure init;
var s:string;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s1);
    readln(s2);
    if pos('*',s2)<>0 then
      begin
        s:=s1;
        s1:=s2;
        s2:=s;
      end;
    close(input);
  end;

procedure done;
  begin
    if check(length(s1),length(s2)) then
      begin
        write('YES');
      end else
      begin
        write('NO');
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end. 

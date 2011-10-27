program z_1195;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:array[0..4] of string;
    q1,q2:integer;
    
function check(x:integer):boolean;
var c:char; i:integer;
  begin
    if (x=1) then c:='X' else c:='O';
    result:=false;
    for i:=1 to 3 do
      begin
        result:=result or ((c=s[i][1]) and (c=s[i][2]) and (c=s[i][3]));
        result:=result or ((c=s[1][i]) and (c=s[2][i]) and (c=s[3][i]));
      end;
    result:=result or ((c=s[1][1]) and (c=s[2][2]) and (s[3][3]=c));
    result:=result or ((c=s[1][3]) and (c=s[2][2]) and (s[3][1]=c));
  end;

procedure print(x:integer);
  begin
    case x of
      0:write('Draw');
      1:write('Crosses win');
      2:write('Ouths win');
    end;
    halt(0);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s[1]);
    readln(s[2]);
    readln(s[3]);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to 3 do
    for j:=1 to 3 do if (s[i][j]='#') then
      begin
        s[i][j]:='X';
        if check(1) then inc(q1);
        s[i][j]:='#';
      end;
    if (q1>0) then print(1);
    for i:=1 to 3 do
    for j:=1 to 3 do if (s[i][j]='#') then
      begin
        s[i][j]:='O';
        if check(2) then inc(q2);
        s[i][j]:='#';
      end;
    if (q2>1) then print(2) else print(0);
  end;

begin
  init;
  solve;
end.

program _0361;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:string;
    a:array[0..101,0..101,'a'..'z'] of integer;
    n:integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
  end;

function check(x1,y1,x2,y2:integer):boolean;
var c:char;
  begin
    result:=false;
    for c:='a' to 'z' do if a[x1,y1,c]<>a[x2,y2,c] then exit;
    result:=true;
  end;

procedure print(x:integer);
  begin
    write(x);
    close(output);
    halt(0);
  end;

procedure solve;
var i,j,k:integer;
  begin
    n:=length(s);
    a[1,1][s[1]]:=1;
    for i:=1 to n-1 do
      begin
        if i<>1 then a[i,i]:=a[i-1,i];
        if i<>1 then dec(a[i,i][s[i-1]]);
        for j:=i+1 to n do
          begin
            a[i,j]:=a[i,j-1];
            inc(a[i,j][s[j]]);
          end;
      end;
    for k:=n-2 downto 1 do
    for i:=1 to n-k-1 do
    for j:=i+1 to n-k do
      begin
        if check(i,i+k,j,j+k) then print(k+1);
      end;
    print(0);
  end;

begin
  init;
  solve;
end.

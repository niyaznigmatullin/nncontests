program _0259;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:array[0..500000] of char;
    maxi,n:integer;
    z:array[0..500000] of integer;

function tupo(x,y:integer):integer;
var i:integer;
  begin
    i:=0;
    while (x<=n+n+1) and (y<=n+n+1) and (s[x]=s[y]) do
      begin
        inc(i);
        inc(x);
        inc(y);
      end;
    result:=i;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do read(s[i]);
    close(input);
  end;

procedure solve;
var j,i,kp:integer;
  begin
    s[n+1]:='$';
    j:=n+1;
    for i:=n downto 1 do
      begin
        inc(j);
        s[j]:=s[i];
      end;
    z[1]:=0;
    maxi:=1;
    for i:=2 to n+n+1 do
      begin
        if z[maxi]+maxi-1>i then
          begin
            kp:=i-maxi+1;
            if kp=maxi then
              begin
                maxi:=i;
                z[i]:=tupo(1,i);
              end else
            if z[kp]+kp-1<z[maxi] then
              begin
                z[i]:=z[kp];
              end else
              begin
                z[i]:=tupo(z[kp]+1,maxi+z[maxi])+z[maxi]-kp+1;
                maxi:=i;                
              end;
          end else
          begin
            z[i]:=tupo(1,i);
            if z[i]>0 then maxi:=i;
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=n+n+1 downto n+2 do write(z[i],' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end. 

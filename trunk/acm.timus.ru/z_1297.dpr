program z_1297;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s:array[0..1001] of char;
    n,maxp,maxi:integer;

procedure init;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    n:=0;
    while not seekeoln do
      begin
        inc(n);
        read(s[n]);
      end;
  end;

procedure solve;
var i,k:integer;
  begin
    maxp:=0;
    for i:=1 to n do
      begin
        k:=0;
        while (i-k>0) and (i+k<=n) do
          begin
            if (k+k+1>maxp) then
              begin
                maxp:=k+k+1;
                maxi:=i-k;
              end;
            inc(k);
            if (s[i-k]=s[i+k]) then else break;
          end;
      end;
    for i:=1 to n-1 do if (s[i]=s[i+1]) then
      begin
        k:=0;
        while (i-k>0) and (i+k+1<=n) do
          begin
            if (k+k+2>maxp) then
              begin
                maxp:=k+k+2;
                maxi:=i-k;
              end;
            inc(k);
            if (s[i-k]=s[i+k+1]) then else break;
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=maxi to maxi+maxp-1 do write(s[i]);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

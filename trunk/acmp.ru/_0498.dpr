program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils;
{$O-}
var dp:array[0..20,0..100000] of integer;
    ans,n,k,nn:integer;

procedure rec(x,mask:integer);
var i:integer;
  begin
    if (dp[x][mask]<>-1) then exit;
    dp[x][mask]:=0;
    for i:=0 to n-1 do if (mask and (1 shl i))<>0 then
      begin
        if (abs(x-i)<=k) then
          begin
            rec(i,mask xor (1 shl i));
            dp[x][mask]:=dp[x][mask]+dp[i][mask xor (1 shl i)];
          end;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,k);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    ans:=0;
    nn:=1 shl n-1;
    for i:=0 to n do
    for j:=0 to nn do
      begin
        dp[i][j]:=-1;
      end;
    for i:=0 to n-1 do
      begin
        dp[i][0]:=1;
      end;
    for i:=0 to n-1 do
      begin
        rec(i,nn-1 shl i);
        ans:=ans+dp[i][nn-1 shl i];
      end;
  end;

procedure done;
  begin
    write(ans);
    close(output);
    halt(0);
  end;
begin
  init;
  solve;
  done;
end.

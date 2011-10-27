program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;
    s:string;
    dp:array[0..40,0..40] of integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    close(input);
    s:=trim(s);
  end;

procedure solve;
var i,k:integer;
  begin
    n:=length(s);
    for i:=1 to n do dp[i][i]:=1;
    for k:=1 to n-1 do
      begin
        for i:=1 to n-k do
          begin
            if (s[i]=s[i+k]) then dp[i][i+k]:=dp[i][i+k-1]+dp[i+1][i+k]+1 else
              dp[i][i+k]:=dp[i][i+k-1]+dp[i+1][i+k]-dp[i+1][i+k-1];
          end;
      end;
  end;

procedure done;
  begin
    write(dp[1][n]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

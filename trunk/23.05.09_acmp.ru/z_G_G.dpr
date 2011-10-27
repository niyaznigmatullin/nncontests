program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils;
  
{$R-}

var n, ko:integer;
    ans, typ:array[0..22] of smallint;
    ex,en:array[0..21,1..2] of smallint;
    kol, all:array[1..2] of integer;
    dp:array[0..1 shl 22 - 1] of integer;
    p : array[0..1 shl 22 - 1] of byte;
    
procedure init;
var i:integer; c:char;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n);
    all[1] := 0;
    all[2] := 0;
    for i:=0 to n-1 do
      begin
        read(c);
        if (c = 'C') then typ[i] := 1 else typ[i] := 2;
        read(ex[i][1], ex[i][2], en[i][1], en[i][2]);
        inc(all[typ[i]]);
        readln;
      end;
    close(input);
  end;

procedure solve;
var i,j:integer; r:integer;
  begin
    dp[0] := 0;
    for i:=1 to 1 shl n - 1 do
      begin
        dp[i] := 1 shl 30;
        kol[1] := 0;
        kol[2] := 0;
        for j:=0 to n-1 do if ((1 shl j) and i) <> 0 then
          begin
            inc(kol[typ[j]]);
          end;
        for j:=0 to n - 1 do if ((1 shl j) and i <> 0) then
          begin
            dec(kol[typ[j]]);
            dec(all[typ[j]]);
            r := dp[i xor (1 shl j)];
            r := r + ex[j][2] * kol[1];
            r := r + ex[j][1] * kol[2];
            r := r + en[j][2] * (all[1] - kol[1]);
            r := r + en[j][1] * (all[2] - kol[2]);
            if (r <= dp[i]) then
              begin
                dp[i] := r;
                p[i] := j;
              end;
            inc(all[typ[j]]);
            inc(kol[typ[j]]);
          end;
      end;
    i := 1 shl n - 1;
    write(dp[i],' ');
    ko := 0;
    while (i > 0) do
      begin
        inc(ko);
        ans[ko] := p[i];
        i := i xor (1 shl p[i]);
      end;
    write(0,' ');
    for i:=ko downto 1 do write(ans[i] + 1,' ');
    write(0);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
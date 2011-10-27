program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..22] of integer;

const base=100000000;

var n,m:integer;
    a:array[0..1025,0..1025] of boolean;
    w:array[0..1025] of integer;
    q:array[0..1024] of boolean;
    kol:integer;
    dp:array[0..100,0..1025] of TLong;
    ans:TLong;

function max(x,y:integer):integer;
  begin
    if (x>y) then result:=x else result:=y;
  end;

procedure inc_long(var a:TLong; const b:TLong);
var um,i,p:integer;
  begin
    a[0]:=max(a[0],b[0]);
    um:=0;
    for i:=1 to a[0] do
      begin
        p:=a[i]+b[i]+um;
        a[i]:=p mod base;
        um:=p div base;
      end;
    if (um>0) then
      begin
        inc(a[0]);
        a[a[0]]:=um;
      end;
  end;

function check(x,y:integer):boolean;
var z,i:integer;
  begin
    z:=y mod (1 shl n);
    y:=y div (1 shl n);
    if (x mod (1 shl n))<>y then
      begin
        result:=false;
        exit;
      end;
    x:=x div (1 shl n);
    for i:=0 to n-1 do
      begin
        if ((x and (1 shl i))=(y and (1 shl i))) and ((z and (1 shl i))=(x and (1 shl i))) then
          begin
            result:=false;
            exit;
          end;
      end;
    result:=true;
  end;

procedure print_long(a:TLong);
var i,p:integer;
  begin
    write(a[a[0]]);
    for i:=a[0]-1 downto 1 do
      begin
        p:=base div 10;
        while (p>a[i]) and (p>1) do
          begin
            write(0);
            p:=p div 10;
          end;
        write(a[i]);
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    close(input);
  end;

procedure solve;
var nn,i,pp,j,k,anss:integer; ok:boolean;
  begin
    nn:=n+n;
    for i:=0 to 1 shl nn-1 do
      begin
        pp:=i div (1 shl n);
        j:=1;
        ok:=true;
        while (j<=n) do
          begin
            k:=j;
            while (k<=n) and (not odd(pp)) do
              begin
                pp:=pp shr 1;
                inc(k);
              end;
            if (k-j>2) then
              begin
                ok:=false;
                break;
              end;
            j:=k;
            while (k<=n) and (odd(pp)) do
              begin
                pp:=pp shr 1;
                inc(k);
              end;
            if (k-j>2) then
              begin
                ok:=false;
                break;
              end;
            j:=k;
          end;
        pp:=i mod (1 shl n);
        j:=1;
        while (j<=n) do
          begin
            k:=j;
            while (k<=n) and (not odd(pp)) do
              begin
                pp:=pp shr 1;
                inc(k);
              end;
            if (k-j>2) then
              begin
                ok:=false;
                break;
              end;
            j:=k;
            while (k<=n) and (odd(pp)) do
              begin
                pp:=pp shr 1;
                inc(k);
              end;
            if (k-j>2) then
              begin
                ok:=false;
                break;
              end;
            j:=k;
          end;
        if (ok) then
          begin
            inc(kol);
            w[kol]:=i;
          end;
      end;
    if (m=1) then
      begin
        fillchar(q,sizeof(q),0);
        for i:=1 to kol do
          begin
            q[w[i] mod (1 shl n)]:=true;
          end;
        anss:=0;
        for i:=0 to 1 shl n do if (q[i]) then inc(anss);
        write(anss);
        close(output);
        halt(0);
      end;
    for i:=1 to kol do
    for j:=1 to kol do if (i<>j) then
      begin
        a[i][j]:=check(w[i],w[j]);
      end else a[i][j]:=false;
    fillchar(dp,sizeof(dp),0);
    for i:=1 to kol do
      begin
        dp[1][i][0]:=1;
        dp[1][i][1]:=1;
      end;
    for i:=2 to m-1 do
    for j:=1 to kol do
    for k:=1 to kol do if (a[j][k]) then
      begin
        inc_long(dp[i][k],dp[i-1][j]);
      end;
    ans[0]:=0;
    ans[1]:=0;
    for i:=1 to kol do
      begin
        inc_long(ans,dp[m-1][i]);
      end;
  end;

procedure done;
  begin
    print_long(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

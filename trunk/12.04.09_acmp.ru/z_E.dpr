{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_E;
{$APPTYPE CONSOLE}
uses
  SysUtils;               

var n,k:integer; m:int64;
    dp:array[0..17, 0..1 shl 17] of int64;
    a:array[0..20] of int64;
    c:array[0..20,0..20] of int64;
    ans:array[0..20] of integer;

function gcd(x,y:int64):int64;
  begin
    while (x*y>0) do
      begin
        if (x>y) then x:=x mod y else y:=y mod x;
      end;
    result:=x+y;
  end;

procedure rec(const x,mask:integer);
var i:integer;
  begin
    if (dp[x][mask]<>-1) then exit;
    dp[x][mask]:=0;
    for i:=0 to n-1 do if ((1 shl i) and mask)<>0 then
      begin
        if (c[x][i]>=k) then
          begin
            rec(i,mask xor (1 shl i));
            dp[x][mask]:=dp[x][mask]+dp[i][mask xor (1 shl i)];
          end;
      end;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m,k);
    for i:=0 to n-1 do read(a[i]);
    close(input);
  end;

procedure solve;
var i,j,mask,nn,p,kol,t:integer;eee:int64;
  begin
    for i:=0 to n-1 do
    for j:=i+1 to n-1 do if (a[i]>a[j]) then begin nn:=a[i];
     a[i]:=a[j];
     a[j]:=nn; end;
     eee:=0;
    for i:=0 to n-1 do
    for j:=0 to 1 shl n do dp[i][j]:=-1;
    for i:=0 to n-1 do dp[i][0]:=1;
    for i:=0 to n-1 do
    for j:=0 to n-1 do if (i<>j) then c[i][j]:=gcd(a[i],a[j]);
    nn:=1 shl n-1;
    for i:=1 to n-1 do
      begin
        for j:=0 to nn-1 do
          begin
            p:=j;
            kol:=0;
            while (p>0) do begin inc(kol); p:=p and (p-1); end;
            if (kol=i) then
              begin
                for t:=0 to n-1 do if (j and (1 shl t))=0 then rec(t,j);
              end;
          end;
      end;
    for i:=0 to n-1 do
      begin
        //rec(i,nn xor (1 shl i));
        eee:=eee+dp[i][nn xor (1 shl i)];
      end;  
    if eee<m then begin
            write(-1);
            close(output);
            halt(0);
          end;
    mask:=nn;
    for i:=1 to n do
      begin
        for j:=0 to n-1 do if ((mask and (1 shl j))<>0) and ((c[ans[i-1],j]>=k) or (i=1))then
          begin
            if (dp[j][mask xor (1 shl j)]<m) then
              begin
                if (dp[j][mask xor (1 shl j)]<>-1) then m:=m-dp[j][mask xor (1 shl j)];
              end else
              begin
                ans[i]:=j;
                mask:=mask xor (1 shl j);
                break;
              end;
          end;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do write(a[ans[i]],' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program _0375;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var d,a,b,c:array[0..10000] of integer;
    s:string;
    um,q,m,n,k:integer;

function cifr(c:char):integer;
  begin
    if c in ['0'..'9'] then result:=ord(c)-ord('0') else
    result:=ord(c)-ord('A')+10;
  end;

function symb(x:integer):char;
  begin
    if x<10 then result:=chr(x+ord('0')) else
    result:=chr(x-10+ord('A'));
  end;

procedure mul_k;
var i,p,um:integer;
  begin
    um:=(a[1]*n) div 10;
    a[1]:=(a[1]*n) mod 10;
    for i:=2 to a[0] do
      begin
        p:=a[i]*n+um;
        a[i]:=p mod 10;
        um:=p div 10;
      end;
    while um>0 do
      begin
        inc(a[0]);
        a[a[0]]:=um mod 10;
        um:=um div 10;
      end;
  end;

procedure add_k(x:integer);
var i,p,um:integer;
  begin
    um:=(a[1]+x) div 10;
    a[1]:=(a[1]+x) mod 10;
    for i:=2 to a[0] do
      begin
        p:=a[i]+um;
        a[i]:=p mod 36;
        um:=p div 36;
      end;
    while um>0 do
      begin
        inc(a[0]);
        a[a[0]]:=um mod 10;
        um:=um div 10;
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,k);
    readln(s);
    close(input);
    s:=trim(s);
    if s='0' then
      begin
        write(0);
        close(output);
        halt(0);
      end;
  end;

procedure solve;
var i,j,p:integer;
  begin
    m:=length(s);
    for i:=1 to m do
      begin
        d[i]:=cifr(s[i]);
      end;
    q:=0;
    a[0]:=1;
    for i:=1 to m-1 do
      begin
        add_k(d[i]);
        mul_k;
      end;
    add_k(d[m]);
    for i:=1 to a[0] div 2 do
      begin
        p:=a[i];
        a[i]:=a[a[0]-i+1];
        a[a[0]-i+1]:=p;
      end;
    m:=a[0];
    while true do
      begin
        inc(q);
        j:=1;
        um:=0;
        while (j<=m) and (a[j]=0) do
          begin
            inc(j);
          end;
        if j>m then
          begin
            dec(q);
            break;
          end;
        for i:=j to m do
          begin
            b[i]:=(a[i]+um*10) div k;
            um:=((a[i]+um*10) mod k);
          end;
        c[q]:=um;
        a:=b;
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=q downto 1 do write(symb(c[i]));
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

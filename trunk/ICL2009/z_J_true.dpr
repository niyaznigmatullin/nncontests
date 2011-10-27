program z_J_true;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

{$o-}
type TLong=array[0..62] of integer;

const base=10000;

var f:array[0..60] of TLong;
    z,a:array[0..60] of integer;
    ans,temp:TLong;
    n:integer;

procedure short_to_long(var a:TLong; const v:integer);
  begin
    fillchar(a,sizeof(a),0);
    a[0]:=1;
    a[1]:=v;
  end;

procedure norm(var a:TLong);
var i:integer;
  begin
    i:=1;
    while (i<=a[0]) do
      begin
        if (a[i]>=base) then
          begin
            inc(a[i+1],a[i] div base);
            a[i]:=a[i] mod base;
            if (i=a[0]) then inc(a[0]);
          end;
        inc(i);
      end;
  end;

procedure long_mul_short(var a:TLong; const v:integer);
var i:integer;
  begin
    for i:=1 to a[0] do a[i]:=a[i]*v;
    norm(a);
  end;

procedure long_div_short(var a:TLong; const v:integer);
var ok:boolean; i,p,um:integer;
  begin
    ok:=false;
    um:=0;
    for i:=a[0] downto 1 do
      begin
        p:=(um*base+a[i]);
        a[i]:=p div v;
        um:=p mod v;
        if (a[i]<>0) and (not ok) then
          begin
            a[0]:=i;
            ok:=true;
          end;
      end;
  end;

procedure inc_long(var a:TLong; const b:TLong);
var i:integer;
  begin
    a[0]:=max(a[0],b[0]);
    for i:=1 to a[0] do
      begin
        inc(a[i],b[i]);
      end;
    norm(a);
  end;

procedure mul_fact(var a:TLong; const v:integer);
var i:integer;
  begin
    for i:=2 to v do long_mul_short(a,i);
  end;

procedure div_fact(var a:TLong; const v:integer);
var i:integer;
  begin
    for i:=2 to v do long_div_short(a,i);
  end;

procedure long_mul(var c:TLong; const a,b:TLong);
var i,j:integer;
  begin
    short_to_long(c,0);
    for i:=1 to a[0] do
      begin
        for j:=1 to b[0] do
          begin
            c[i+j-1]:=c[i+j-1]+a[i]*b[j];
            c[0]:=max(c[0],i+j-1);
          end;
        norm(c);
      end;
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
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var k,c,i,p,j,kol:integer;
  begin
    short_to_long(f[0],1);
    for i:=1 to n-1 do
      begin
        short_to_long(f[i],0);
        for j:=0 to i-1 do
          begin
            short_to_long(temp,0);
            long_mul(temp,f[j],f[i-j-1]);
            mul_fact(temp,i-1);
            div_fact(temp,j);
            div_fact(temp,i-j-1);
            long_mul_short(temp,j+1);
            inc_long(f[i],temp);
          end;
      end;
    short_to_long(ans,1);
    k:=0;
    for i:=1 to n do inc(k,1-a[i]);
    if (k<n) then
      begin
        c:=1;
        while (a[c]=0) do inc(c);
        kol:=1;
        z[1]:=c-1;
        p:=0;
        for i:=c to n do
          begin
            if (a[i]=1) then
              begin
                if (p>0) then
                  begin
                    inc(kol);
                    z[kol]:=p;
                  end;
                p:=0;
              end else inc(p);
          end;
        inc(z[1],p);
        mul_fact(ans,k);
        for i:=1 to kol do div_fact(ans,z[i]);
        for i:=1 to kol do
          begin
            temp:=ans;
            long_mul(ans,temp,f[z[i]]);
          end;
      end else
      begin
        for i:=1 to n do
          begin
            long_mul_short(ans,n);
          end;
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

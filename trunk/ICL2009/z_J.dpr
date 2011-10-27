program z_J;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..22] of integer;

const base=100000000;

var qq,m,p,q:integer;
    y,pp,a,b,c,d:array[0..200] of integer;
    u,x:array[0..200] of boolean;
    ans:TLong;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(m);
    read(p);
    for i:=0 to p do read(a[i]);
    read(q);
    for i:=0 to q do read(b[i]);
    close(input);
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
procedure solve;
var i,j,v,um,t,kol:integer; ok:boolean;
  begin
    qq:=0;
    for i:=0 to m-1 do
      begin
        c[i]:=0;
        for j:=0 to p do
          begin
            c[i]:=(c[i]*i+a[j]) mod m;
          end;
        for j:=0 to q do
          begin
            d[i]:=(d[i]*i+b[j]) mod m;
          end;
      end;
    for i:=0 to m-1 do
      begin
        fillchar(u,sizeof(u),false);
        v:=i;
        while (not u[v]) do
          begin
            u[v]:=true;
            pp[c[v]]:=v;
            v:=c[v];
          end;
        if (v=i) then
          begin
            kol:=0;
            fillchar(x,sizeof(x),false);
            repeat
              begin
                inc(kol);
                y[kol]:=v;
                x[v]:=true;
                v:=pp[v];
              end;
            until v=i;
            ok:=true;
            for j:=1 to kol do
              begin
                ok:=ok and x[d[j]];
              end;
            if (ok) then
              begin
                inc(qq);
                continue;
              end;
          end;
      end;
    ans[0]:=1;
    ans[1]:=1;
    for i:=1 to qq do
      begin
        um:=0;
        for j:=1 to ans[0] do
          begin
            t:=ans[j]+ans[j]+um;
            ans[j]:=t mod base;
            um:=t div base;
          end;
        if (um>0) then
          begin
            inc(ans[0]);
            ans[ans[0]]:=um;
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

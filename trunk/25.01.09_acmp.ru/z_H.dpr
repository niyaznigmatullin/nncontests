program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

const eps=1e-6;

var kol,n,k1,k2:integer;
    d:extended;
    s,s1,s2:array[0..1001] of string;
    o,w,p,ans,r,r1,r2:array[0..1001] of extended;
    c,pn,po:array[0..1001] of integer;

procedure init;
var c:char; i:integer; ss:string;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(n,d,k1,k2);
    for i:=1 to n do
      begin
        read(c);
        ss:='';
        while c<>' ' do
          begin
            ss:=ss+lowercase(c);
            read(c);
          end;
        s[i]:=trim(ss);
        readln(r[i]);
      end;
    readln;
    for i:=1 to k1 do
      begin
        read(c);
        ss:='';
        while c<>' ' do
          begin
            ss:=ss+lowercase(c);
            read(c);
          end;
        s1[i]:=trim(ss);
        readln(r1[i]);
      end;
    readln;
    for i:=1 to k2 do
      begin
        read(c);
        ss:='';
        while c<>' ' do
          begin
            ss:=ss+lowercase(c);
            read(c);
          end;
        s2[i]:=trim(ss);
        readln(r2[i]);
      end;
    close(input);
  end;

procedure solve;
var i,j,g:integer; e:extended;
  begin
    r1[0]:=1e20;
    r2[0]:=1e20;
    for i:=1 to n do
      begin
        pn[i]:=0;
        for j:=1 to k1 do
          begin
            if (s[i]=s2[j]) then begin pn[i]:=j; break; end;
          end;
        po[i]:=0;
        for j:=1 to k2 do
          begin
            if (s[i]=s1[j]) then begin po[i]:=j; break; end;
          end;
        if (pn[i]=0) then ans[i]:=0 else
          begin
            inc(kol);
            w[kol]:=r[i]*r1[po[i]];
            p[kol]:=r2[pn[i]];
            c[kol]:=i;
          end;
      end;
    for i:=1 to kol-1 do
    for j:=i+1 to kol do
    if (w[i]/p[i]<w[j]/p[j]) then
      begin
        e:=p[i];
        p[i]:=p[j];
        p[j]:=e;
        e:=w[i];
        w[i]:=w[j];
        w[j]:=e;
        g:=c[i];
        c[i]:=c[j];
        c[j]:=g;
      end;
    for i:=1 to kol do
      begin
        if (o[c[i]]<=0) then break;
        ans[c[i]]:=min(d/r1[po[c[i]]],r[c[i]]);
        d:=d-min(d,r[c[i]]*r1[po[c[i]]]);
      end;
  end;

procedure done;
var i:integer;
  begin
    for i:=1 to n do
      begin
        writeln(ans[i]:0:9);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

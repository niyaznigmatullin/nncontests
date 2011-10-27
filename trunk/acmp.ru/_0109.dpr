program _0109;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s,s1,s2:string;
    a,b,kol:integer;
    c,d:array[0..1001] of integer;

procedure init;
var c:char;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    c:=' ';
    while c<>'/' do
      begin
        read(c);
        if c<>'/' then s1:=s1+c;
      end;
    a:=strtoint(s1);
    readln(s2);
    s2:=trim(s2);
    b:=strtoint(s2);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    write(a div b);
    a:=a mod b;
    if a=0 then exit;
    write('.');
    fillchar(c,sizeof(c),0);
    fillchar(d,sizeof(d),0);
    kol:=0;
    c[1]:=a;
    while a<>0 do
      begin
        inc(kol);
        a:=a*10;
        s:=s+inttostr(a div b);
        d[kol]:=a div b;
        a:=a mod b;
        for i:=1 to kol do if a=c[i] then
          begin
            for j:=1 to i-1 do write(d[j]);
            write('(');
            for j:=i to kol do write(d[j]);
            write(')');
            exit;
          end;
        c[kol+1]:=a;
      end;
    write(s);
  end;

procedure done;
  begin
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 
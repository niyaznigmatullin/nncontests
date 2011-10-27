program _0356;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var e,f,n:integer;
    p,w:array[0..501] of integer;
    a,b:array[0..10001] of integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(e,f);
    dec(f,e);
    read(n);
    for i:=1 to n do read(p[i],w[i]);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    a[0]:=0;
    b[0]:=0;
    for i:=0 to f-1 do
    if (a[i]<>0) or (i=0) then
      begin
        for j:=1 to n do
          begin
            if i+w[j]<=f then
              begin
                if (a[i+w[j]]=0) or (a[i+w[j]]>a[i]+p[j]) then a[i+w[j]]:=a[i]+p[j];
                if (b[i+w[j]]=0) or (b[i+w[j]]<b[i]+p[j]) then b[i+w[j]]:=b[i]+p[j];
              end;
          end;
      end;
  end;

procedure done;
  begin
    if a[f]=0 then write('This is impossible.') else write(a[f],' ',b[f]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var s,q1,q2,ss:string;
    n:integer;
    z,x:array[#0..#255] of integer;

procedure init;
  begin
    reset(input,'prefixes.in');
    rewrite(output,'prefixes.out');
    readln(s);
    close(input);
  end;

procedure solve;
var i:integer; c:char;
  begin
    n:=length(s);
    for i:=1 to n do inc(z[s[i]]);
    c:='a';
    ss:=s;
    i:=1;
    q1:='';
    x:=z;
    while (c<='z') and (i<=length(ss)) do
      begin
        while (c<'z') and (x[c]=0) do inc(c);
        if x[c]=0 then break;
        dec(x[ss[i]]);
        if ss[i]=c then
          begin
            q1:=q1+c;
            delete(ss,i,1);
            dec(i);
          end;
        inc(i);
      end;
    for i:=1 to length(ss) div 2 do
      begin
        c:=ss[i];
        ss[i]:=ss[length(ss)-i+1];
        ss[length(ss)-i+1]:=c;
      end;
    c:='a';
    q1:=q1+ss;    
    ss:=s;
    q2:='';
    i:=length(ss);
    x:=z;
    while i>=1 do
      begin
        while (c<'z') and (x[c]=0) do inc(c);
        if x[c]=0 then break;
        dec(x[ss[i]]);
        if ss[i]=c then
          begin
            q2:=q2+c;
            delete(ss,i,1);
          end;
        dec(i);
      end;
    q2:=q2+ss;
  end;

procedure done;
  begin
    if q1>q2 then write(q2) else write(q1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

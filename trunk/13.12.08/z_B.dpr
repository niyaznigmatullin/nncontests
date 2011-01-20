program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;
  
type TMas=array['a'..'z'] of integer;

var a:TMas;
    e:integer;
    lastc:char;
    res,ss:string;

function rec:string;
var c:char; e,i:integer; ok:boolean;
  begin
    e:=0;
    ok:=false;
    for c:='a' to 'z' do begin if odd(a[c]) then begin inc(e); lastc:=c; end; if a[c]<>0 then ok:=true; end;
    if not ok then begin result:=''; exit; end;
    if e>1 then
      begin
        result:='';
        for c:='a' to 'z' do for i:=1 to a[c] do result:=result+c;
        exit;
      end;
    for c:='a' to 'z' do a[c]:=a[c] div 2;
    if e=1 then result:=lastc else result:='';
    ss:=rec;
    result:=ss+result;
    for i:=length(ss) downto 1 do result:=result+ss[i];
  end;

procedure init;
var c:char;
  begin
    reset(input,'paly4.in');
    rewrite(output,'paly4.out');
    e:=0;
    for c:='a' to 'z' do
      begin
        read(a[c]);
        if odd(a[c]) then begin inc(e); {lastc:=c;} end;
      end;
    close(input);
  end;

procedure solve;
var c:char; i:integer;
  begin
    if e>1 then
      begin
        for c:='a' to 'z' do for i:=1 to a[c] do write(c);
        close(output);
        halt(0);
      end;
    res:=rec;
  end;

procedure done;
  begin
    write(res);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

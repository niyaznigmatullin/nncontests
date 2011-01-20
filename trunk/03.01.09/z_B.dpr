program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var l,r:int64;
    a:array[0..20,0..20,0..20] of int64;
    d,o:array[0..20] of integer;
    u:array[0..20] of int64;

function count(x:int64):int64;
var p,i,j,f,one,two:integer; e:int64;
  begin
    if x=0 then
      begin
        result:=0;
        exit;
      end;
    for i:=0 to 19 do begin d[i]:=integer(x mod 10); x:=x div 10; end;
    p:=19;
    while d[p]=0 do dec(p);
    e:=0;
    fillchar(o,sizeof(o),0);
    for i:=0 to p-1 do inc(e,u[i+1]);
    one:=0;
    two:=10;
    for i:=p downto 0 do
      begin
        f:=1;
        if i<p then f:=0;
        for j:=f to d[i]-1 do
          begin
            if o[j]=2 then continue;
            inc(o[j]);
            if o[j]=1 then begin dec(two); inc(one); end else dec(one);
            inc(e,a[i,one,two]);
            dec(o[j]);
            if o[j]=0 then
              begin
                inc(two);
                dec(one);
              end else inc(one);
          end;
        inc(o[d[i]]);
        if o[d[i]]>2 then
          begin
            result:=e;
            exit;
          end;
        if o[d[i]]=1 then
          begin
            dec(two);
            inc(one);
          end else dec(one);
      end;
      inc(e);
      result:=e;
  end;

procedure init;
var i,j,k:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(l,r);
    for i:=0 to 10 do
      begin
        j:=0;
        while j+i<=10 do
          begin
            a[0,i,j]:=1;
            inc(j);
          end;
      end;
    for i:=1 to 19 do
      begin
        for j:=0 to 10 do
          begin
            k:=0;
            while j+k<=10 do
              begin
                if (j>0) then inc(a[i,j,k],j*a[i-1,j-1,k]);
                if (k>0) then inc(a[i,j,k],k*a[i-1,j+1,k-1]);
                inc(k);
              end;
          end;
      end;
    for i:=1 to 19 do
      begin
        u[i]:=a[i,0,10]-a[i-1,1,9];
      end;
    close(input);
  end;

procedure done;
  begin
    write(count(r)-count(l-1));
    close(output);
    halt(0);
  end;

begin
  init;
  done;
end.
 
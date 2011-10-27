program _0223;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TLong=array[0..20] of int64;

const base=10000000;

var u:array[0..51,0..51,0..51] of boolean;
    q:array[0..51,0..51,0..51] of TLong;
    ans:TLong;
    s1,s2:string;

function max(x,y:integer):integer;
  begin
    if x>y then result:=x else result:=y;
  end;

procedure long_inc(var c:TLong; const a:TLong);
var um,i,p:integer;
  begin
    um:=0;
    c[0]:=max(c[0],a[0]);
    for i:=1 to c[0] do
      begin
        p:=a[i]+c[i]+um;
        c[i]:=p mod base;
        um:=p div base;
      end;
    if um>0 then begin inc(c[0]); c[c[0]]:=um; end;
  end;

procedure long_mul(var c:TLong; const a,b:TLong);
var p:int64; k,i,j:integer;
  begin
    fillchar(c,sizeof(c),0);
    for i:=1 to a[0] do
      begin
        for j:=1 to b[0] do
          begin
            k:=i+j-1;
            p:=c[k]+a[i]*b[j];
            c[k]:=p mod base;
            c[k+1]:=c[k+1]+p div base;
            repeat
              inc(k);
              c[k+1]:=c[k+1]+c[k] div base;
              c[k]:=c[k] mod base;
            until c[k+1]=0;
          end;
      end;
    c[0]:=a[0]+b[0]+2;
    while (c[c[0]]=0) and (c[0]>1) do dec(c[0]);
    while c[c[0]]>=base do
      begin
        c[c[0]+1]:=c[c[0]] div base;
        c[c[0]]:=c[c[0]] mod base;
        inc(c[0]);
      end;
  end;

function rec(x,y,len:integer):TLong;
var i:integer; sum,temp:TLong;
  begin
    if not u[x,y,len] then
      begin
        if len=0 then
          begin
            fillchar(sum,sizeof(sum),0);
            sum[0]:=1;
            sum[1]:=1;
          end else
          begin
            fillchar(sum,sizeof(sum),0);
            sum[0]:=1;
            for i:=1 to len do if s1[x]=s2[y+i-1] then
              begin
                long_mul(temp,rec(x+1,y,i-1),rec(x+i,y+i,len-i));
                long_inc(sum,temp);
              end;
          end;
        q[x,y,len]:=sum;
        u[x,y,len]:=true;
        result:=sum;
      end else
      begin
        result:=q[x,y,len];
      end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s1);
    readln(s2);
    close(input);
  end;

procedure solve;
  begin
    fillchar(u,sizeof(u),false);
    ans:=rec(1,1,length(s1));
  end;

procedure done;
var i:integer;
  begin
    write(ans[ans[0]]);
    for i:=ans[0]-1 downto 1 do
      begin
        if ans[i]<1000000 then write(0);
        if ans[i]<100000 then write(0);
        if ans[i]<10000 then write(0);
        if ans[i]<1000 then write(0);
        if ans[i]<100 then write(0);
        if ans[i]<10 then write(0);
        write(ans[i]);
      end;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

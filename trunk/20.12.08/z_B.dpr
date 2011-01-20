program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TType=record
      x,y,c:integer;
      end;

const di:array[1..4] of integer=(1,1,1,0);
      dj:array[1..4] of integer=(1,-1,0,1);

var a,b,c,d:array[0..1001] of TType;
    kol,n,m:integer;
    ok1,ok2:boolean;
    w:array[0..1001] of integer;
    u:array[0..1001,0..10] of boolean;

function find_min:integer;
var res,i,j:integer;
  begin
    res:=maxint;
    w[0]:=maxint;
    result:=maxint;
    for i:=1 to n-4 do
      begin
        if res=w[i-1] then
          begin
            res:=0;
            for j:=i to i+4 do if res<w[j] then res:=w[j];
          end else if w[i+4]>res then res:=w[i+4];
        if result>res then result:=res;
      end;
  end;

procedure incons;
  begin
    write('Inconsistent');
    close(output);
    halt(0);
  end;

function find1(x,y:integer):integer;
var lo,hi,mid:integer;
  begin
    lo:=1;
    hi:=m;
    while lo<=hi do
      begin
        mid:=(lo+hi) div 2;
        if (c[mid].x=x) and (c[mid].y=y) then
          begin
            result:=c[mid].c;
            exit;
          end else
        if (c[mid].x<x) or ((c[mid].x=x) and (c[mid].y<y)) then lo:=mid+1 else hi:=mid-1;
      end;
    result:=0;
  end;

function find2(x,y:integer):integer;
var lo,hi,mid:integer;
  begin
    lo:=1;
    hi:=n div 2;
    while lo<=hi do
      begin
        mid:=(lo+hi) div 2;
        if (d[mid].x=x) and (d[mid].y=y) then
          begin
            result:=d[mid].c;
            exit;
          end else
        if (d[mid].x<x) or ((d[mid].x=x) and (d[mid].y<y)) then lo:=mid+1 else hi:=mid-1;
      end;
    result:=0;
  end;

procedure sort1(l,r:integer);
var i,j:integer; x,y:TType;
  begin
    i:=l;
    j:=r;
    x:=c[random(r-l+1)+l];
    repeat
      while (c[i].x<x.x) or ((c[i].x=x.x) and (c[i].y<x.y)) do inc(i);
      while (c[j].x>x.x) or ((c[j].x=x.x) and (c[j].y>x.y)) do dec(j);
      if i<=j then
        begin
          y:=c[i];
          c[i]:=c[j];
          c[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort1(i,r);
    if l<j then sort1(l,j);
  end;

procedure sort2(l,r:integer);
var i,j:integer; x,y:TType;
  begin
    i:=l;
    j:=r;
    x:=d[random(r-l+1)+l];
    repeat
      while (d[i].x<x.x) or ((d[i].x=x.x) and (d[i].y<x.y)) do inc(i);
      while (d[j].x>x.x) or ((d[j].x=x.x) and (d[j].y>x.y)) do dec(j);
      if i<=j then
        begin
          y:=d[i];
          d[i]:=d[j];
          d[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort2(i,r);
    if l<j then sort2(l,j);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'gomoku.in');
    rewrite(output,'gomoku.out');
    read(n);
    for i:=1 to n do
      begin
        if odd(i) then
        read(a[i div 2+1].x,a[i div 2+1].y) else read(b[i div 2].x,b[i div 2].y);
      end;
    for i:=1 to n div 2+1 do a[i].c:=i+i-1;
    for i:=1 to n div 2 do b[i].c:=i+i;
    close(input);
  end;

procedure solve;
var i,t,x1,x2,y1,y2,r,j,p,e:integer;
  begin
    c:=a;
    d:=b;
    if odd(n) then m:=n div 2+1 else m:=(n-1) div 2+1;
    sort1(1,m);
    sort2(1,n div 2);
    ok1:=false;
    for i:=1 to m do
      begin
        for t:=1 to 4 do
        if not u[i,t] then
          begin
            kol:=1;
            w[kol]:=i;
            x1:=a[i].x;
            x2:=x1;
            y1:=a[i].y;
            y2:=y1;
            while true do
              begin
                dec(x1,di[t]);
                dec(y1,dj[t]);
                r:=find1(x1,y1);
                if r<>0 then
                  begin
                    inc(kol);
                    w[kol]:=r;
                  end else break;
              end;
            for j:=1 to kol div 2 do begin p:=w[j]; w[j]:=w[kol-j+1]; w[kol-j+1]:=p; end;
            while true do
              begin
                inc(x2,di[t]);
                inc(y2,dj[t]);
                r:=find1(x2,y2);
                if r<>0 then
                  begin
                    inc(kol);
                    w[kol]:=r;
                  end else break;
              end;
            for j:=1 to kol do
              begin
                u[w[j],t]:=true;
              end;
            if kol>5 then incons;
            if kol=5 then
              begin
                e:=find_min;
                if e<n then incons; 
                ok1:=true;
                break;
              end;
          end;
        if ok1 then break;
      end;
    fillchar(u,sizeof(u),false);
    ok2:=false;
    for i:=1 to n div 2 do
      begin
        for t:=1 to 4 do
        if not u[i,t] then
          begin
            kol:=1;
            w[kol]:=i;
            x1:=b[i].x;
            x2:=x1;
            y1:=b[i].y;
            y2:=y1;
            while true do
              begin
                dec(x1,di[t]);
                dec(y1,dj[t]);
                r:=find2(x1,y1);
                if r<>0 then
                  begin
                    inc(kol);
                    w[kol]:=r;
                  end else break;
              end;
            for j:=1 to kol div 2 do begin p:=w[j]; w[j]:=w[kol-j+1]; w[kol-j+1]:=p; end;
            while true do
              begin
                inc(x2,di[t]);
                inc(y2,dj[t]);
                r:=find2(x2,y2);
                if r<>0 then
                  begin
                    inc(kol);
                    w[kol]:=r;
                  end else break;
              end;
            for j:=1 to kol do
              begin
                u[w[j],t]:=true;
              end;
            if kol>5 then incons;
            if kol=5 then
              begin
                e:=find_min;
                if e<n then incons;
                ok2:=true;
                break;
              end;
          end;
        if ok2 then break;
      end;
  end;

procedure done;
  begin
    if not ok1 and not ok2 then write('Draw') else
    if ok1 then write('First player won') else write('Second player won');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.

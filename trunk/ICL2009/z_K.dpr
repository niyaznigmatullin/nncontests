program z_K;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const months:array[1..12] of string=(
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
        'July',
        'August',
        'September',
        'October',
        'November',
        'December'
    );
    maxn = 100;
    maxy = 100;

type TDate=record
      m,d:integer;
     end;

function read_string:string;
var r:string; c:char;
  begin
    read(c);
    while not (c in ['0'..'9', 'A'..'Z', 'a'..'z']) do read(c);
    r:='';
    while (c in ['0'..'9', 'A'..'Z', 'a'..'z']) do
      begin
        r:=r+c;
        read(c);
      end;
    result:=r;
  end;

function read_month:integer;
var s:string; i:integer;
  begin
    s:=read_string;
    for i:=1 to 12 do if months[i]=s then
      begin
        result:=i;
        exit;
      end;
    result:=0;
  end;

function read_date:TDate;
var t:TDate;
  begin
    t.m:=read_month;
    t.d:=strtoint(read_string);
    result:=t;
  end;

function gt(d1,d2:TDate):boolean;
  begin
    result:=(d1.m>d2.m) or ((d1.m=d2.m) and (d1.d>d2.d));
  end;

function eq(d1,d2:TDate):boolean;
  begin
    result:=(d1.m=d2.m) and (d1.d=d2.d);
  end;

function is_leap(y:integer):boolean;
  begin
    result:=((y mod 4=0) and not (y mod 100=0)) or (y mod 400=0);
  end;

procedure print_date(d:TDate);
  begin
    write(months[d.m],' ',d.d);
  end;

var x,h:array[1..maxn] of TDate;
    c:array[1..maxn] of boolean;
    cnt,c29:array[0..maxn] of integer;
    dp,f:array[0..maxy,0..maxn] of longint;
    n:integer;
    ys,yf:longint;
    s:string;

procedure print(i,j:integer);
var k,l:integer;
  begin
    k:=f[i][j];
    if i>0 then print(i-1,k);
    for l:= k+1 to j do
      begin
        print_date(x[l]);
        write(' ',ys+i-1);
        if c[l] then write(', added ') else write(', removed ');
        print_date(h[l]);
        writeln;
    end;
end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(ys,yf);
    read(n);
    for i:=1 to n do
      begin
        x[i]:=read_date;
        s:=read_string;
        if (s='added') then
          begin
            c[i]:=true;
          end else if (s='removed') then
          begin
            c[i]:=false;
          end;
        h[i]:=read_date;
      end;
    close(input);
  end;

procedure solve;
var i,j,k,l,t:integer;
  begin
    cnt[0]:=0;
    c29[0]:=0;
    for i:=1 to n do
      begin
        cnt[i]:=cnt[i-1];
        c29[i]:=c29[i-1];
        if (h[i].m=2) and (h[i].d=29) then
          begin
            if (c[i]) then c29[i]:=c29[i-1]+1 else c29[i]:=c29[i-1]-1;
          end else
          begin
            if (c[i]) then cnt[i]:=cnt[i-1]+1 else cnt[i]:=cnt[i-1]-1;
          end;
      end;
    for j:=1 to n do dp[0][j]:=-1;
    dp[0][0]:=0;
    for i:=1 to yf-ys+1 do
      begin
        dp[i][0]:=0;
        for j:=1 to n do
          begin
            if (dp[i-1][j]>=0) then
              begin
                dp[i][j]:=dp[i-1][j]+cnt[j];
                if (is_leap(ys+i-1)) then
                  begin
                    dp[i][j]:=dp[i][j]+c29[j];
                  end;
                f[i][j]:=j;
              end else
                begin
                  dp[i][j]:=-1;
                end;
            if (is_leap(ys+i-1) or (x[j].m<>2) or (x[j].d<>29)) then
              begin
                for k:=j-1 downto 0 do
                  begin
                    if (dp[i-1][k]>=0) then
                      begin
                        t:=cnt[k];
                        if (is_leap(ys+i-1)) then t:=t+c29[k];
                        for l:=k+1 to j do
                          begin
                            if (not c[l]) and(gt(h[l],x[l])) then
                              begin
                                if (is_leap(ys+i-1)) or (h[l].m<>2) or (h[l].d<>29) then dec(t);
                              end;
                            if (c[l]) and (gt(h[l],x[l])) then
                              begin
                                if (is_leap(ys+i-1) or (h[l].m<>2) or (h[l].d<>29)) then inc(t);
                              end;
                          end;
                        if (dp[i][j]=-1) or (dp[i-1][k]+t>dp[i][j]) then
                          begin
                            dp[i][j]:=dp[i-1][k]+t;
                            f[i][j]:=k;
                          end;
                      end;
                    if (k>0) and ((not gt(x[k+1],x[k])) or ((not is_leap(ys+i-1)) and (x[k].m=2) and (x[k].d=29))) then
                      begin
                        break;
                      end;
                  end;
              end;
          end;
      end;
  end;

procedure done;
  begin
    writeln(dp[yf-ys+1][n]);
    if (dp[yf-ys+1][n]<>-1) then print(yf-ys+1,n);
    close(output);
    halt(0);
  end;


begin
  init;
  solve;
  done;
end.

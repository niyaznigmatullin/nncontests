program _0236;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var varr,num,minus:array[0..1000] of boolean;
    s:string;
    v:longint;q:int64;
    st,ch:array[0..1000] of int64;

function step(x:shortint):longint;
  begin
    result:=1;
    case x of
      0:result:=v;
      1:result:=v;
      2:result:=v*v;
      3:result:=v*v*v;
      4:result:=v*v*v*v;
    end;
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    readln(s);
    read(v);
    close(input);
  end;

procedure solve;
var i,j:longint;
  begin
    fillchar(varr,sizeof(varr),false);
    fillchar(num,sizeof(num),false);
    fillchar(st,sizeof(st),0);
    fillchar(ch,sizeof(ch),0);
    fillchar(minus,sizeof(minus),false);
    if s[1]='-' then
      begin
        minus[1]:=true;
        delete(s,1,1);
      end;
    j:=1;
    i:=1;
    while i<=length(s) do
      begin
        if (s[i]='-') or (s[i]='+') then
          begin
            inc(j);
            if s[i]='-' then minus[j]:=true;
            inc(i);
            continue;
          end else
        if s[i] in ['0'..'9'] then
          begin
            while s[i] in ['0'..'9'] do
              begin
                ch[j]:=ch[j]*10;
                inc(ch[j],ord(s[i])-ord('0'));
                inc(i);
                if i>length(s) then break;
              end;
            num[j]:=true;
            if i>length(s) then break;
          end else
        if s[i]='x' then
          begin
            varr[j]:=true;
            inc(i);
            if i>length(s) then break;
            if s[i]='^' then
              begin
                inc(i);
                st[j]:=ord(s[i])-ord('0');
                inc(i);
              end else continue;
            if i>length(s) then break;
          end;
        if s[i]='*' then
          begin
            varr[j]:=true;
            inc(i,2);
            if i>length(s) then break;
            if s[i]='^' then
              begin
                inc(i);
                st[j]:=ord(s[i])-ord('0');
                inc(i);
              end else continue;
          end;
      end;
    for i:=1 to j do
      begin
        if varr[i] then
          begin
            if num[i] then
              begin
                if minus[i] then dec(q,ch[i]*step(st[i])) else
                inc(q,ch[i]*step(st[i]));
              end else
              begin
                if minus[i] then dec(q,step(st[i])) else
                inc(q,step(st[i]));
              end;
          end else
          begin
            if minus[i] then dec(q,ch[i]) else inc(q,ch[i]);
          end;
      end;
  end;

procedure out;
  begin
    write(q);
    close(output);
  end;

begin
  init;
  solve;
  out;
end.    

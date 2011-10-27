const
   numlen=100;
type
   number=array[1..numlen]of byte;

   procedure set0(var n:number);
   var
      i:integer;
   begin
      for i:=1 to numlen do
         n[i]:=0;
   end;

   procedure set1(var n:number);
   begin
      set0(n);
      n[1]:=1;
   end;

   procedure add(var n1,n2:number);
   var
      i:integer;
      c:integer;
   begin
      c:=0;
      for i:=1 to numlen do begin
         c:=c+n1[i]+n2[i];
         n1[i]:=c mod 10;
         c:=c div 10;
      end;
      if c<>0 then
         halt(1);
   end;

   procedure show(n:number);
   var
      i:integer;
   begin
      i:=numlen;
      while (i>1)and(n[i]=0) do
         dec(i);
      for i:=i downto 1 do
         write(n[i]);
      writeln;
   end;

const
   key:array['A'..'Z']of integer=(
      1,1,1,
      2,2,2,
      3,3,3,
      4,4,4,
      5,5,5,
      6,6,6,6,
      7,7,7,
      8,8,8,8);
   count:array['A'..'Z']of integer=(
      1,2,3,
      1,2,3,
      1,2,3,
      1,2,3,
      1,2,3,
      1,2,3,4,
      1,2,3,
      1,2,3,4);
   maxp:array[1..8]of integer=(3,3,3,3,3,4,3,4);
var
   n,i,j,last:integer;
   s:string;
   a:array[1..320]of integer;
   cnt:array[0..320,0..80]of number;
   alen:integer;
   c:char;
begin
   assign(input,'input.txt');
   reset(input);
   assign(output,'output.txt');
   rewrite(output);
   readln(n);
   readln(s);
   alen:=0;
   for i:=1 to length(s) do begin
      c:=s[i];
      c:=upcase(c);
      for j:=1 to count[c] do begin
         inc(alen);
         a[alen]:=key[c];
      end;
   end;
   for i:=0 to alen do
      for j:=0 to n do
         set0(cnt[i,j]);
   set1(cnt[0,0]);
   for i:=1 to alen do begin
      for last:=1 to 4 do begin
         if last>i then
            break;
         if last>maxp[a[i]] then
            break;
         if (last>1)and(a[i+1-last]<>a[i]) then
            break;
         for j:=1 to n do
            add(cnt[i,j],cnt[i-last,j-1]);
      end;
   end;
   show(cnt[alen,n]);
end. 

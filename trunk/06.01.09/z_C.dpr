program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var
   table:array[1..7000]of record
      num:int64;
      wins:boolean;
   end;
   tablen:integer;
   nn:int64;

   procedure inittable;
   begin
      tablen:=0;
   end;

   function indexOf(n:int64):integer;
   var
      l,r,mid:integer;
   begin
      l:=1;
      r:=tablen;
      while l<=r do begin
         mid:=(l+r)div 2;
         if table[mid].num<n then
            l:=mid+1
         else if table[mid].num>n then
            r:=mid-1
         else begin
            indexOf:=mid;
            exit;
         end;
      end;
      indexOf:=-1;
   end;

   function isInTable(n:int64):boolean;
   begin
      isInTable:=indexOf(n)>0;
   end;

   procedure put(n:int64);
   var
      i:integer;
   begin
      if n>=nn then
         exit;
      if isInTable(n) then
         exit;
      if tablen=high(table) then begin
         writeln('overflow');
         halt(1);
      end;
      i:=tablen;
      while (i>0)and(table[i].num>n) do begin
         table[i+1]:=table[i];
         dec(i);
      end;
      table[i+1].num:=n;
      inc(tablen);
   end;

   function wins(n:int64):boolean;
   begin
      wins:=table[indexOf(n)].wins;
   end;

var
   t:int64;
   i,j:integer;
   canwin:boolean;
begin
   assign(input,'input.txt');
   reset(input);
   assign(output,'output.txt');
   rewrite(output);
   readln(nn);
   inittable;
   put(1);
   i:=1;
   while i<=tablen do begin
      t:=table[i].num;
      for j:=2 to 9 do
         put(t*j);
      inc(i);
   end;
   for i:=tablen downto 1 do begin
      t:=table[i].num;
      if t*9>=nn then
         table[i].wins:=true
      else begin
         canwin:=false;
         for j:=2 to 9 do
            if not wins(t*j) then begin
               canwin:=true;
               break;
            end;
         table[i].wins:=canwin;
      end;
   end;
   if table[1].wins then
      writeln('Stan wins.')
   else
      writeln('Ollie wins.');
end.

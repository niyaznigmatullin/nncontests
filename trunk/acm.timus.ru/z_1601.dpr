program z_1601;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var c:char;
    is_it:boolean;

begin
  is_it := true;
  while (not eof) do
    begin
      read(c);
      if (not is_it) and (c in ['A'..'Z']) then c:=chr(ord(c) - ord('A') + ord('a'));
      if (c in ['A'..'Z']) and (is_it) then
          begin
            is_it:=false;
          end else if (c in ['!','?','.']) then is_it:=true;
      write(c);
    end;
end.
 
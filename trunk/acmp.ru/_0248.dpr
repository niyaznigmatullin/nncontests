 var i,j,o,r : string;
k: integer;
begin
assign(INPUT,'input.txt');reset(INPUT);
assign(OUTPUT,'output.txt');rewrite(OUTPUT);
readln(i);
j:=' '+i+'   ';
k:=2;
o:='';
r:='';
while k<=length(i)+1 do begin
  if (j[k] in ['a','A']) AND NOT (j[k-1] in ['a'..'z','A'..'Z'])       {a}
                 AND NOT (j[k+1] in ['a'..'z','A'..'Z']) then begin
    inc(k);
    if j[k]=' ' then inc(k);
  end
  else
    if (j[k] in ['t','T']) AND (j[k+1]='h') AND (j[k+2]='e')
                   AND NOT (j[k-1] in ['a'..'z','A'..'Z'])
                   AND NOT (j[k+3] in ['a'..'z','A'..'Z']) then begin  {the}
      k:=k+3;
      if j[k]=' ' then inc(k);
    end
    else
      if (j[k] in ['a','A']) AND (j[k+1]='n')                           {an}
                   AND NOT (j[k-1] in ['a'..'z','A'..'Z'])
                   AND NOT (j[k+2] in ['a'..'z','A'..'Z']) then begin
        k:=k+2;
        if j[k]=' ' then inc(k);
      end
      else begin
        if (j[k]='c') OR (j[k]='C') then begin
          inc(k);
          if (j[k]='e') OR (j[k]='i') then begin
            if (j[k-1]='c') then begin
              o:=o+'s'
            end
            else begin
              o:=o+'S'
            end;
          end
          else begin
            if j[k]<>'k' then begin
              if j[k-1]='c' then begin
                o:=o+'k'
              end
              else begin
                o:=o+'K'
              end;
            end
            else begin
              if j[k-1]='C' then begin
                j[k]:='K'
              end
            end;
          end;
        end
        else begin
          if (j[k]='e') OR (j[k]='E') then begin
            inc(k);
            if j[k]='e' then begin
              if j[k-1]='e' then begin
                o:=o+'i';
              end
              else begin
                o:=o+'I';
              end;
              inc(k);
            end
            else begin
              if (j[k] in ['a'..'z','A'..'Z']) OR NOT (j[k-2] in ['a'..'z','A'..'Z']) then begin
                o:=o+j[k-1];
              end;
            end
          end
          else begin
            if (j[k]='o') or (j[k]='O') then begin
              inc(k);
              if j[k]='o' then begin
                if j[k-1]='O' then begin
                  o:=o+'U';
                end
                else begin
                  o:=o+'u';
                end;
                inc(k);
              end
              else begin
                o:=o+j[k-1];
              end;
            end
            else begin
              o:=o+j[k];
              inc(k);
            end;
          end;
        end;
      end;
end;
o:='*'+o;
for k:=length(o) downto 2 do begin
 if NOT ((o[k] in ['a'..'z']) AND ((o[k-1]=o[k]) OR (ord(o[k-1])=ord(o[k])-32))) OR
        ((o[k] in ['A'..'Z']) AND ((o[k-1]=o[k]) OR (ord(o[k-1])=ord(o[k])+32)))then
   r:=r+o[k];
end;
for k:=length(r) downto 1 do begin
write(r[k]);
end;
close(input);
close(output);
end. 
 

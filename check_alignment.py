import struct
import sys
import os
import glob

def check_alignment(filepath):
    try:
        with open(filepath, 'rb') as f:
            header = f.read(64)
            if header[:4] != b'\x7fELF':
                return False, "Not an ELF"
            
            is_64 = header[4] == 2
            e_phoff = struct.unpack('<Q' if is_64 else '<I', header[32:40] if is_64 else header[28:32])[0]
            e_phnum = struct.unpack('<H', header[56:58] if is_64 else header[44:46])[0]
            
            f.seek(e_phoff)
            
            for _ in range(e_phnum):
                if is_64:
                    p_type, p_flags, p_offset, p_vaddr, p_paddr, p_filesz, p_memsz, p_align = struct.unpack('<IIQQQQQQ', f.read(56))
                else:
                    p_type, p_offset, p_vaddr, p_paddr, p_filesz, p_memsz, p_flags, p_align = struct.unpack('<IIIIIIII', f.read(32))
                
                # PT_LOAD is 1
                if p_type == 1:
                    if p_align != 16384 and p_align != 65536:
                        return False, f"Alignment is {p_align}"
                        
            return True, "Aligned"
    except Exception as e:
        return False, str(e)

if __name__ == '__main__':
    for lib in glob.glob("scanlibrary/src/main/libs/**/*.so", recursive=True):
        is_aligned, msg = check_alignment(lib)
        if not is_aligned:
            print(f"{lib}: {msg}")

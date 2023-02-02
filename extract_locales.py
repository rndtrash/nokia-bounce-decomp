#!/usr/bin/env python3

import os
from mutf8 import decode_modified_utf8

TRANSLATED_STRINGS = 14
TRANSLATION_FILE_PATH = "src/main/resources/lang.xx"

def read_short(f):
    b = f.read(2)
    return int.from_bytes(b, byteorder='big')

file_size = os.path.getsize(TRANSLATION_FILE_PATH)
with open(TRANSLATION_FILE_PATH, "rb") as locale_file:
    offsets = []
    for _ in range(TRANSLATED_STRINGS):
        offsets.append(read_short(locale_file))

    count = 0
    for offset in offsets:
        locale_file.seek(offset)
    
        length = read_short(locale_file)
        
        string = decode_modified_utf8(locale_file.read(length))
        print(f"Locale #{count}: \"{string}\"")
        count += 1
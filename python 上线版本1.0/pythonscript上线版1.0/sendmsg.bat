@echo off
set path=C:\Python27\;
cd C:\Program Files\pythonscript
python sendmessage.py %1 %2 >>log.txt
@exit
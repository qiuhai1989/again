@echo off
rem ���ñ���
title �ҵ�������
rem ���ÿ���̨ǰ���ͱ���ɫ ��ɫ����������ʮ����������ָ�� -- ��һ��Ϊ�������ڶ�����Ϊǰ��
color 06
rem mode ����ϵͳ�豸
rem ����DOS���ڴ�С��15�У�113��
mode con cols=213 lines=45 & color 9f

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
:: string1��string2��Ϊ�ַ������ݣ�Ӣ�����ַ��Ĵ�Сд��������ͬ����������еĵ��ںű�����������������ȵ���˼��������Ⱥ�ִ�к����command
	if  [abc]==[abc] echo �ַ������
	if not [abc]==[abd] echo �ַ��������
:: IF [NOT] EXIST filename command
:: EXIST filenameΪ�ļ���Ŀ¼���ڵ���˼
	IF EXIST autoexec.bat echo �ļ����ڣ�
	IF not EXIST autoexec.bat echo �ļ������ڣ�

:: ��������
::1.�������ȡ����ʱ�ǰ��ж�ȡ�ģ���������for����ȣ������һ��Բ���űպϵ��������Ҳ����һ�У����ڴ���֮ǰҪ��ɱ�Ҫ��Ԥ�������������оͰ����Ը� �������еı�����ֵ
set a=4
set a=5 & echo %a%

::2.��Ϊ���ܹ���֪���������Ķ�̬�仯������������˱����ӳ١�����˵���ڶ�ȡ��һ�����������֮�󣬲������Ը��еı�����ֵ��������ĳ���������ִ��֮ǰ�ٽ��и�ֵ��Ҳ����˵���ӳ١��˶Ա����ĸ�ֵ��
::���������ӳ�--�����ӳٵ���������ǡ�setlocal enabledelayedexpansion�������ұ���Ҫ��һ��̾�š�!!����������ע��Ҫ��Ӣ�ĵ�̾�ţ��������û�б����ӳٵ�Ч��
setlocal enabledelayedexpansion
set a=4
set a=5 & echo !a!

::& ������� &��&&��||Ϊ����������˼�壬���ǿ��԰Ѷ���������������һ��������ִ��
echo aa & echo bb
::&& ������� �����ַ�������ͬʱִ�ж������������ִ�г��������󽫲�ִ�к����������һֱû�г�����һֱִ������������
::||  ������� �����ַ�������ͬʱִ�ж��������һ������ʧ�ܺ��ִ�еڶ������������ִ����ȷ������󽫲�ִ�к����������û�г�����ȷ��������һֱִ�����������

::�ֺţ���������ͬʱ�����Խ���ͬĿ���ã������룬��ִ��Ч�����䣬��ִ�й����з���������ֻ���ش��󱨸棬�������Ի�ִ��
::dir E:\CODE\abp;E:\myProject\again

::() ���� С��������������������������ã��������ű���ɶ�ʹ�ã������п��԰������������Щ���������һ�����壬��Ϊһ��������
(
echo 1
echo 2
echo 3
)

:: for ѭ�� FOR %%variable IN (set) DO command [command-parameters]
:: ���� /d ������а���ͨ�������ָ����Ŀ¼��ƥ�䣬�������ļ���ƥ��
for /d %%i in (c:\*) do echo %%i

for /L %%i in (0,1,5) do echo %%i
:: ���� /R FOR /R [[drive:]path] %%variable IN (set) DO command [command-parameters] ���� [drive:]path Ϊ����Ŀ¼����ָ��ÿ��Ŀ¼�е�FOR ���
::for /r c:\ %%i in (*.exe) do echo %%i
:: �ѵ�ǰĿ¼������xml�ļ��оٳ���
::for  /r E:\CODE\abp  %%i in (*.xml) do @echo %%i
::for  /r E:\CODE\abp  %%i in (*.xml) do if exist %%i @echo %%i

rem ϵͳ���б���
echo %os%
echo %CD%
echo %DATE%
echo %USERNAME%
echo %WINDIR%

echo.
rem �Զ������
set var=����ֵ
echo %var%

set /p var=�����������ֵ
echo %var%


set var=0
rem ************ѭ����ʼ��
:continue
set /a var+=1
echo ��%var%��ѭ��
if %var% lss 100 goto continue
rem ************ѭ��������
echo ѭ��ִ�����




rem ��ͣ
pause

echo %errorlevel%
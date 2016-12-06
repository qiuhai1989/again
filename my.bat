@echo off
rem 设置标题
title 我的批处理
rem 设置控制台前景和背景色 颜色属性由两个十六进制数字指定 -- 第一个为背景，第二个则为前景
color 06
rem mode 配置系统设备
rem 设置DOS窗口大小：15行，113列
mode con cols=113 lines=15 & color 9f

rem 输出提示信息
echo "欢迎来到非常BAT!"
echo "欢迎来到非常BAT!2"
rem 输出空行，即相当于输入一个回车 
echo.
echo "欢迎来到非常BAT!3"

rem GOTO 和 :
:start
set /a var+=1
echo %var%
if %var% leq 3 GOTO start

:: errorlevel
echo 上一个命令运行结果如下：%errorlevel%



rem find 在文件中搜索字符串
echo 111 >test.txt
echo 222 >>test.txt
echo 111 >>test.txt
type test.txt|find "111" 
del test.txt

:: start 命令 批处理中调用外部程序的命令（该外部程序在新窗口中运行，批处理程序继续往下执行，不理会外部程序的运行状况），如果直接运行外部程序则必须等外部程序完成后才继续执行剩下的指令
:: 调用图形界面打开D盘
:: start explorer d:\

:: call CALL命令可以在批处理执行过程中调用另一个批处理，当另一个批处理执行完后，再继续执行原来的批处理
:: 

set aa=123456
set cmdstr=echo %aa%
call %cmdstr%

:: IF IF 条件判断语句，语法格式如下：
:: IF [NOT] ERRORLEVEL number command 这个句子必须放在某一个命令的后面，执行命令后由IF ERRORLEVEL 来判断命令的返回值
	call %cmdstr%
	rem 退出代码为>=1就跳至标题1处执行，>=0就跳至标题0处执行
	IF ERRORLEVEL 1 goto 1
	IF ERRORLEVEL 0 goto 0
	Rem 上面的两行不可交换位置，否则失败了也显示成功。
	:0
	echo 命令执行成功！
	Rem 程序执行完毕跳至标题exit处退出
	goto exit
	:1
	echo 命令执行失败！
	Rem 程序执行完毕跳至标题exit处退出
	goto exit
	:exit
:: IF [NOT] string1==string2 command
:: IF [NOT] EXIST filename command
::










rem 暂停
pause

echo %errorlevel%
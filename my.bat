@echo off
rem 设置标题
title 我的批处理
rem 设置控制台前景和背景色 颜色属性由两个十六进制数字指定 -- 第一个为背景，第二个则为前景
color 06
rem mode 配置系统设备
rem 设置DOS窗口大小：15行，113列
mode con cols=213 lines=45 & color 9f

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
:: string1和string2都为字符的数据，英文内字符的大小写将看作不同，这个条件中的等于号必须是两个（绝对相等的意思）条件相等后即执行后面的command
	if  [abc]==[abc] echo 字符串相等
	if not [abc]==[abd] echo 字符串不相等
:: IF [NOT] EXIST filename command
:: EXIST filename为文件或目录存在的意思
	IF EXIST autoexec.bat echo 文件存在！
	IF not EXIST autoexec.bat echo 文件不存在！

:: 变量定义
::1.批处理读取命令时是按行读取的（另外例如for命令等，其后用一对圆括号闭合的所有语句也当作一行），在处理之前要完成必要的预处理工作，这其中就包括对该 行命令中的变量赋值
set a=4
set a=5 & echo %a%

::2.而为了能够感知环境变量的动态变化，批处理设计了变量延迟。简单来说，在读取了一条完整的语句之后，不立即对该行的变量赋值，而会在某个单条语句执行之前再进行赋值，也就是说“延迟”了对变量的赋值。
::开启变量延迟--变量延迟的启动语句是“setlocal enabledelayedexpansion”，并且变量要用一对叹号“!!”括起来（注意要用英文的叹号），否则就没有变量延迟的效果
setlocal enabledelayedexpansion
set a=4
set a=5 & echo !a!

::& 组合命令 &、&&、||为组合命令，顾名思义，就是可以把多个命令组合起来当一个命令来执行
echo aa & echo bb
::&& 组合命令 用这种方法可以同时执行多条命令，当碰到执行出错的命令后将不执行后面的命令，如果一直没有出错则一直执行完所有命令
::||  组合命令 用这种方法可以同时执行多条命令，当一条命令失败后才执行第二条命令，当碰到执行正确的命令后将不执行后面的命令，如果没有出现正确的命令则一直执行完所有命令；

::分号，当命令相同时，可以将不同目标用；来隔离，但执行效果不变，如执行过程中发生错误，则只返回错误报告，但程序仍会执行
::dir E:\CODE\abp;E:\myProject\again

::() 括号 小括号在批处理编程中有特殊的作用，左右括号必须成对使用，括号中可以包括多行命令，这些命令将被看成一个整体，视为一条命令行
(
echo 1
echo 2
echo 3
)

:: for 循环 FOR %%variable IN (set) DO command [command-parameters]
:: 参数 /d 如果集中包含通配符，则指定与目录名匹配，而不与文件名匹配
for /d %%i in (c:\*) do echo %%i

for /L %%i in (0,1,5) do echo %%i
:: 参数 /R FOR /R [[drive:]path] %%variable IN (set) DO command [command-parameters] 查以 [drive:]path 为根的目录树，指向每个目录中的FOR 语句
::for /r c:\ %%i in (*.exe) do echo %%i
:: 把当前目录下所有xml文件列举出来
::for  /r E:\CODE\abp  %%i in (*.xml) do @echo %%i
::for  /r E:\CODE\abp  %%i in (*.xml) do if exist %%i @echo %%i

rem 系统已有变量
echo %os%
echo %CD%
echo %DATE%
echo %USERNAME%
echo %WINDIR%

echo.
rem 自定义变量
set var=我是值
echo %var%

set /p var=请输入变量的值
echo %var%


set var=0
rem ************循环开始了
:continue
set /a var+=1
echo 第%var%次循环
if %var% lss 100 goto continue
rem ************循环结束了
echo 循环执行完毕




rem 暂停
pause

echo %errorlevel%
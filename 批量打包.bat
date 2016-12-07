@echo off
rem 设置标题
title 我的批处理

echo %cd%


rem 根据实际情况配置好
set settings="%USERPROFILE%\.m2\settings.xml"
set localPath="D:\M2_REPO_NEXUS"
set version=1.0-SNAPSHOT

rem 过程中是否暂停 1-暂停 0-不暂停
set isPause=1
set mvnparm=-s %settings% -Dfile.encoding=UTF-8
echo mvnparm:%mvnparm%
set warparm=org.apache.maven.plugins:maven-war-plugin:2.3:war -Dmaven.test.skip=true
echo warparm:%warparm%

call mvn clean 
call mvn install  -Dmaven.test.skip=true
echo 重新清空安装完毕
rem ---abp.service
rem call mvn %mvnparm% %warparm% -f again.service/pom.xml
rem echo war again finish

rem 将打包好的war 复制到一个文件夹中

del /Q /S /F target
mkdir target
cd target

copy /y ..\again.web\target\again.web-%version%.war
copy /y ..\again.service\target\again.service-%version%.war
copy /y ..\again.jms\target\again.jms-%version%.war
rem 删除指定配置
WinRAR d again.service-%version%.war WEB-INF\classes\config
WinRAR d again.service-%version%.war WEB-INF\classes\log4j.properties

echo -----------------------------------
echo -----------------------------------
echo 已经打好包了，放到到target目录下了，按任意键退出
echo -----------------------------------

pause


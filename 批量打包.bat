@echo off
rem ���ñ���
title �ҵ�������

echo %cd%


rem ����ʵ��������ú�
set settings="%USERPROFILE%\.m2\settings.xml"
set localPath="D:\M2_REPO_NEXUS"
set version=1.0-SNAPSHOT

rem �������Ƿ���ͣ 1-��ͣ 0-����ͣ
set isPause=1
set mvnparm=-s %settings% -Dfile.encoding=UTF-8
echo mvnparm:%mvnparm%
set warparm=org.apache.maven.plugins:maven-war-plugin:2.3:war -Dmaven.test.skip=true
echo warparm:%warparm%

call mvn clean 
call mvn install  -Dmaven.test.skip=true
echo ������հ�װ���
rem ---abp.service
rem call mvn %mvnparm% %warparm% -f again.service/pom.xml
rem echo war again finish

rem ������õ�war ���Ƶ�һ���ļ�����

del /Q /S /F target
mkdir target
cd target

copy /y ..\again.web\target\again.web-%version%.war
copy /y ..\again.service\target\again.service-%version%.war
copy /y ..\again.jms\target\again.jms-%version%.war
rem ɾ��ָ������
WinRAR d again.service-%version%.war WEB-INF\classes\config
WinRAR d again.service-%version%.war WEB-INF\classes\log4j.properties

echo -----------------------------------
echo -----------------------------------
echo �Ѿ���ð��ˣ��ŵ���targetĿ¼���ˣ���������˳�
echo -----------------------------------

pause


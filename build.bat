echo ����winscp�ű�Ŀ¼Ϊ��ǰĿ¼
set scriptPath =%cd%

#echo ����winscp����Ŀ¼
#set winscpPath=e:\WinSCP

echo ����core����Ŀ¼
set corePath=E:\game\core

echo ����center����Ŀ¼
set centerPath=E:\game\center

echo ����dao����Ŀ¼
set daoPath=E:\game\dao


echo ����gateway����Ŀ¼
set gatewayPath=E:\game\gateway

echo ����protocol����Ŀ¼
set protocolPath=E:\game\protocol

echo ����scene����Ŀ¼
set scenePath=E:\game\scene

echo ����utils����Ŀ¼
set utilsPath=E:\game\utils

echo  "##############################	��	ʼ	��	��	��	Ŀ  ############################"

cd %corePath%
call mvn install

echo  "##############################	core	��	��	��	��  ############################"
ping /n 1 127.1 >nul

cd %daoPath%
call  mvn install

echo  "##############################	dao	��	��	��	��  ############################"
ping /n 1 127.1 >nul

cd %utilsPath%
call mvn install

echo  "##############################	utils	��	��	��	��  ############################"
ping /n 1 127.1 >nul



cd %protocolPath%
call mvn install

echo  "##############################	protocol	��	��	��	��  ############################"
ping /n 1 127.1 >nul


cd %gatewayPath%
call mvn install

echo  "##############################	gateway	��	��	��	��  ############################"
ping /n 1 127.1 >nul

cd %scenePath%
call mvn install

echo  "##############################	scene	��	��	��	��  ############################"
ping /n 1 127.1 >nul



cd %centerPath%
call mvn install

echo  "##############################	center	��	��	��	��  ############################"
ping /n 1 127.1 >nul



pause
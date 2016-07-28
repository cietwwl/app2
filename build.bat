echo 设置winscp脚本目录为当前目录
set scriptPath =%cd%

#echo 设置winscp命令目录
#set winscpPath=e:\WinSCP

echo 设置core工程目录
set corePath=E:\game\core

echo 设置center工程目录
set centerPath=E:\game\center

echo 设置dao工程目录
set daoPath=E:\game\dao


echo 设置gateway工程目录
set gatewayPath=E:\game\gateway

echo 设置protocol工程目录
set protocolPath=E:\game\protocol

echo 设置scene工程目录
set scenePath=E:\game\scene

echo 设置utils工程目录
set utilsPath=E:\game\utils

echo  "##############################	开	始	构	建	项	目  ############################"

cd %corePath%
call mvn install

echo  "##############################	core	构	建	完	成  ############################"
ping /n 1 127.1 >nul

cd %daoPath%
call  mvn install

echo  "##############################	dao	构	建	完	成  ############################"
ping /n 1 127.1 >nul

cd %utilsPath%
call mvn install

echo  "##############################	utils	构	建	完	成  ############################"
ping /n 1 127.1 >nul



cd %protocolPath%
call mvn install

echo  "##############################	protocol	构	建	完	成  ############################"
ping /n 1 127.1 >nul


cd %gatewayPath%
call mvn install

echo  "##############################	gateway	构	建	完	成  ############################"
ping /n 1 127.1 >nul

cd %scenePath%
call mvn install

echo  "##############################	scene	构	建	完	成  ############################"
ping /n 1 127.1 >nul



cd %centerPath%
call mvn install

echo  "##############################	center	构	建	完	成  ############################"
ping /n 1 127.1 >nul



pause
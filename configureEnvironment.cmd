@echo on
call echo Limpiando el Ambiente...
call cmd\killJava
call echo Descargando Java...
call cmd\downloadJavaInstaller
call echo Instalando Java...
call cmd\installJava
call echo Eliminando Instalador...
call cmd\deleteJavaInstaller
call echo Instalando Tools...
call cmd\installTools
call cmd\installChocolate
call cmd\installCompillers
call echo Compilando el proyecto...
call cmd\install
call echo Creando el paquete...
call cmd\package
call echo Ejecutando App...
call cmd\runJavaFX
call echo App ejecutada!
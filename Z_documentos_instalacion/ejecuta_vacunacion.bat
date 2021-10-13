@echo off
color 2
TITLE Krugercorp - Vacunacion Java
java  -DDATA_URL=jdbc:postgresql://localhost:5432/inventario_vacunacion -DDATA_USER=postgres -DDATA_PASS=adminadmin -jar vacunacion-0.0.1.jar

PAUSE
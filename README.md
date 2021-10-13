   #[RETO APLICACION KRUGERCORP]()
   ###INSTALACION DE ENTORNO NUEVO
 #####Inventario de vacunación de empleados
######Contenido:
    1) Requisitos
    2) Pasos para la instalacion
    3) Creacion de archivo jar
    4) Despliege en DOCKER
    5) Información Developer
#####Nota: los archivos se encuentran en el directorio 
####./Z_documentos_instalacion/
       1) BD_EsquemaBk.backup
       2) BK_Data_vacunacion.backup
       3) docker-compose.yaml
       4) ejecuta_vacunacion.bat
       5) Modelo de Datos inventario_vacunacion.pdf
#####Nota.- La base de datos consta de un diccionario de datos en sus scripts
######Requisitos:
    * Open JDK 8 o superior (si fuera superior configurar con la versión de java en el tag <properties> del archivo pom.xml
    * Maven
    * Postgresql
    * Conexión a internet para descarga de repositorios y Clonación de proyecto
#####Pasos para la instalación:

   1) Instalar Postgresql, si ya lo tiene instalado vaya al paso 2.
   2) Crear la base de datos inventario_vacunacion:
       * Querys que puede usar
       * CREATE DATABASE inventario_vacunacion
         ENCODING = 'UTF8' TABLESPACE = pg_default CONNECTION LIMIT = -1;
     
        * COMMENT ON DATABASE inventario_vacunacion
         IS 'Base de datos para inventariar la vacunación de los empleados';

   3) Conectar con la base de datos inventario_vacunacion
   4) Ejecutar el script BD_esquema_BK
   5) Ejecutar el script BK_Data_vacunacion
   6) Clonar del repositorio [https://github.com/agutierrezagc/registro_vacunacion.git](https://github.com/agutierrezagc/registro_vacunacion.git)
   7) Abrir el Proyecto con el IDE de su preferencia
       * Establecer los parámetros de conexión a Base de datos en el archivo var.env
   8) Ejecutar la aplicación
   9) Una vez levantada la aplicación diríjase a:
        * [http://localhost:8090/swagger-ui.html](http://localhost:8090/swagger-ui.html)
        * en Swagger diríjase a [/empleado/login/](http://localhost:8090/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/empleado-controller/login)  clic en (Try it out)
            * Ingrese "usuario": "Alvaro.777111", clave=777111
            * Copie el token de la respuesta exceptuando Bearer
            * Clic en el botón Authorize (en la parte superior de la página) con icono de candado, se abrirá una ventana (Available authorizations).
            * Pegar el token copiado en la ventana pop up
            * Cerrar la ventana
            * Ya puede acceder a los end Points según el Rol
            * El usuario Alvaro.777111 tiene rol Administrador
            * Se recomienda Crear otro usuario con rol Administrador
            * Crear nuevos usuarios
            * Por defecto el usuario que se crea tiene el siguiente formato:
                * Formato Usuario -> PrimerNombre.cedulaSinCerosDelante
                * Formato Clave   -> Cedula_10_Digitos
10) Solo Usuarios Administradores pueden Acceder a reportes
11) Contraseñas con cifrado PBE PBEKeySpec (seguridad alta)
####Creación de archivo Jar
######Comandos:
    1) Situarse en el directorio de proyecto
    
        * mvn clean
        * mvn install -DskipTest
    
    2) En caso no escape los test ejecutar uno de los siguiente comandos
        * mvn install -Dmaven.test.skip=true
        * mvn -Dmaven.test.skip=true install 
      
    3) En la carpeta /target/ se encuentra el archivo jar para ejecucion
        * vacunacion-0.0.1.jar
     
    4) Se adjunta el archivo .bat con variables de entorno para ejecucion
        * ejecuta_vacunacion.bat  (modificar variables de entorno de este archivo para conexion a base de datos)
    
####Despliege en DOCKER 
######Comandos Docker-compose:
    1) Debe tener instalado docker y docker-compose en su equipo
        * se dejo un archivo docker-compose.yaml con la configuracion nesesaria
    
    2) Crear un directorio Ej:
        * mkdir vacunacion_registro
    
    3) Crear el directorio "target" dentro de vacunacion_registro
        * cd vacunacion_registro
        * mkdir target
    
    4) Copiar el archivo vacunacion-0.0.1.jar en "target"
    
    5) Copiar el archivo docker-compose.yaml en vacunacion_registro
    
    6) En el directorio vacunacion_registro ejecutar el siguiente comando:
        * docker-compose up 
    7) Descargara las imagenes nesesarias para funcionar
    
    8) Conectarse con algun cliente de bases de datos Ej DBeaber
        * Crear la base de datos si no estubiera creada
        * Ejecutar el script BD_esquema_BK
        * Ejecutar el script BK_Data_vacunacion
    
    9) ejecutar los siguientes comandos
        * ctrl c para cerrar la ejecucion 
        * docker-compose up -d
### Desarrollado en fecha 10/2021 por:
* ##### Alvaro Gutierrez Copa
* ##### WhatsApp 70682262 
* ##### Celular  68008056 
* ##### Email    alvarito.agc4@gmail.com
 
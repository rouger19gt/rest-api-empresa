# rest-api-empresa

# Respuestas de la parte teórica

## 1. ¿Qué es un API REST y cómo se diferencia de SOAP?

Un API REST  es un estilo arquitectónico para crear servicios web que utilizan los protocolos HTTP para permitir la comunicación entre sistemas. REST usa métodos HTTP estándar como GET, POST, PUT, DELETE para operar con recursos representados en formatos como JSON o XML. Es simple, flexible y fácil de consumir.

SOAP en cambio, es un protocolo más rígido que usa XML para el intercambio de mensajes y define un conjunto más estricto de reglas. SOAP es más formal y tiene características integradas para seguridad y transacciones, pero suele ser más complejo y pesado que REST.

Su principal diferencia es que REST es más ligero y fácil de usar para aplicaciones web modernas, mientras que SOAP se utiliza en sistemas donde se requiere mayor seguridad o formalidad en el mensaje.

---

## 2. ¿Qué son y para qué son útiles los hilos? Da un ejemplo.

Los hilos  son unidades de ejecución dentro de un programa que permiten realizar múltiples tareas al mismo tiempo, aprovechando mejor el procesador. Son útiles para que una aplicación pueda hacer varias cosas simultáneamente, como escuchar la entrada del usuario mientras procesa datos o realiza operaciones largas en segundo plano.

Por ejemplo, en una aplicación que descarga archivos, un hilo puede encargarse de la descarga mientras otro actualiza la interfaz para que el usuario vea el progreso sin que la aplicación se congele.

---

## 3. ¿Qué estructuras de datos se pueden utilizar para almacenar y manipular colecciones de elementos en Java?

Java ofrece varias estructuras para manejar colecciones, principalmente a través del framework Collection*. Algunas de esas son:

- **List**: Una lista ordenada que permite elementos duplicados. Ejemplo: `ArrayList`, `LinkedList`.
- **Set**: Colección que no permite duplicados. Ejemplo: `HashSet`, `TreeSet`.
- **Map**: Almacena pares clave-valor para buscar datos rápidamente. Ejemplo: `HashMap`, `TreeMap`.

---

## 4. ¿Para manejo de base de datos qué herramienta se puede utilizar en Spring Boot?

En Spring Boot, una herramienta muy popular para manejar bases de datos es Spring Data JPA. Esta herramienta facilita la interacción con bases de datos relacionales usando Java Persistence API (JPA). Permite mapear clases Java a tablas de la base de datos y realizar operaciones CRUD (crear, leer, actualizar, eliminar) de forma sencilla sin escribir consultas SQL explícitas. Además, Spring Boot soporta integración con muchas bases de datos y tiene soporte para otras tecnologías como JDBC, MongoDB, entre otras.

---

## 5. ¿En qué se basa la autenticación por JWT y en qué se diferencia de la autenticación básica?

La autenticación por JWT  se basa en un token firmado digitalmente que contiene la información del usuario y sus permisos. Cuando un usuario inicia sesión, el servidor genera este token y se lo envía al cliente, que lo envía en cada petición para validar su identidad sin necesidad de enviar la contraseña cada vez.

En contraste, la autenticación básica envía el nombre de usuario y contraseña codificados en base64 en cada solicitud. Esto es menos seguro y requiere que el servidor valide las credenciales en cada petición. JWT permite sesiones sin estado, es decir, el servidor no necesita guardar sesión, lo que mejora la escalabilidad y seguridad.

---

## 6. De forma general ¿Qué es y para qué sirven los pipelines en CI/CD?

Los pipelines en CI/CD (Integración Continua y Entrega/Despliegue Continuo) son procesos automatizados que permiten compilar, probar, y desplegar código de forma rápida y confiable. 

Son útiles para asegurar que cada cambio en el código fuente pase por etapas de validación antes de llegar a producción, reduciendo errores y acelerando el desarrollo. Por ejemplo, un pipeline puede compilar el código, ejecutar pruebas automáticas, y si todo pasa, desplegar la nueva versión a un servidor o a producción.

# Scrips de la BD
create database empresa_prueba
go

use empresa_prueba
go

create table usuario(
	usuario varchar(50) primary key not null,
	password varchar(50) not null,
);

create table empleado(
	id int primary key identity(1,1),
	nombre varchar(100) not null,
	apellidos varchar(100) not null,
	fecha_nacimiento date not null,
	genero char(1) check (genero in ('M', 'F')),
	numero_identificacion varchar(50) unique not null
);


insert into usuario(usuario, password)
values('admin', 'admin');

# Scrips de la BD
##Por parte de JAVA
Es necesario cambiar la configuración de las propiedades de la BD en spring boot, tales como el usuario y password, estableciendo las credenciales que tenga configuradas, esto se realiza en el archivo application.properties que está en el directorio de src-main-resource.

##Por parte de Angular
En lso servicios, si la api se está corriendo en una ip local en específica, debe de cambiar las URL para que funcione correctamente. Caso contrario se le puede dejar con localhost

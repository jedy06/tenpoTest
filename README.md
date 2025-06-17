# Nombre del Proyecto: tenpo-test

***
Microservicio tenpo

- Permite hacer el calculo porcentual de la suma de dos numeros que son recibidos por parametros desde un json
- El porcentaje es obtenido a parti de un servicio externo 

## Colaboradores

| ***Rol***     | Responsable           |
|---------------|-----------------------|
| Desarrollador | Jheferson Bastidas    |


## Tabla de Contenidos

1. [Información General](#información-general)
2. [Flujo](#flujo)
3. [Tecnologías](#tecnologías)
4. [Instalación](#instalación)
5. [Documentación](#documentación)
6. [Métodos Disponibles para CommissionsController](#métodos-disponibles-para-commissionscontroller)


***

## Información General

***
Este microservicio Calcula la sumatoria de dos numeros, los cuales son recibidos en formato json, el porcentaje
es obtenido mediante el consumo de un servicio externo que proporciona un numero aleatorio que va desde el 1 hasta
el 100. url del servicio https://684ddac665ed087139174625.mockapi.io/api/percent/percent/2.

El consumo del servicio externo se ejecuta automaticamente con un cron job de Springboot repetitivo, cada llamado se 
registra en una base de datos PostgreSQL la cual guarda el historial de llamados al servicio con su respectiva 
respuesta.

El valor del porcentaje obtenido es guardado en cache por 30 min, lo cual implica que si llega un valor nuevo a la base
de datos o se modifica el registro en uso el servicio seguira respondindo el mismo valor del porcentaje a calcular por 
este periodo de tiempo

***
## Flujo
***
- Un job progragamdo cada 25 min invoca al servicio externo obteniendo el valor numerico del porcentaje a calcular.
- Cada una de estas llamadas quedan registradas en una base de datos PostgreSql.
- Al hacer un llamdo al endpoint de calculo el mismo toma los tres parametros los cuales son el numero1 el numero2 y el
valor del porcentaje al calular, suma los dos numeros y a ese resultado aplica el porcentaje solicitado.
- Al hacer el llamado al endpoint de historial el mismo impmrime todos los llamados al servicio externo de forma 
paginada.
***


***

## Tecnologías:

### <ins>La API está construida utilizando las siguientes tecnologías:</ins>

- [Java](https://www.java.com/es/) 21: Lenguaje de programación utilizado para desarrollar la API.

- [Spring Boot](https://docs.spring.io/spring-boot/index.html) 3.5.0: Framework que simplifica el desarrollo de
  aplicaciones web y servicios REST en Java.

- [Maven](https://maven.apache.org/) 3.6+: Herramienta de gestión de dependencias y construcción de proyectos Java, que
  facilita la compilación y empaquetado de la API.

- [Swagger](https://swagger.io/): Herramienta de código abierto que proporciona documentación interactiva de la API
  RESTful, permitiendo a los usuarios explorar y probar los endpoints.

- [Caffeine](https://github.com/ben-manes/caffeine): Libreria para gestionar datos en cache.

***

# Instalación:

## Clona el repositorio:

 ```
 cd existing_repo
 git clone https://github.com/jedy06/tenpoTest.git
 cd tenpoTest
 git checkout main
 docker compose up
```

***

# Documentación:

## Swagger:

Este proyecto utiliza Swagger para ofrecer documentación interactiva de la API.

```
http://localhost:8081/swagger-ui/index.html
```

***

## Métodos Disponibles para tenpo-Controller:

### Método POST: Obtiene el calculo del porcentaje para dos numeros.

* **Ruta:** `/calculo`
* **Descripción:** Este método permite sumar dos numeros y calcular un porcentaje.
* **Ejemplo de Uso:**

 ```
curl -X 'POST' \
  'http://localhost:8081/calculo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "numero1": 10,
  "numero2": 20
}'
 ```

### Código de estado HTTP

* **200:** Description: OK
  
* **500 Internal Server Error:** Error interno en el servidor.

### Método GET: Obtiene El historial de llamados a la api externa.

* **Ruta:** `/historial`
* **Descripción:** Este método permite mostrar el historial de llamados a la api externa de forma paginada.
* **Ejemplo de Uso:**

 ```
curl -X 'GET' \
  'http://localhost:8081/historial?page=0&size=100&sort=fecha&sort=desc' \
  -H 'accept: */*'
 ```

### Código de estado HTTP

* **200:** Description: OK
 
* **500 Internal Server Error:** Error interno en el servidor.

### Método GET: Verifica si el servicio está disponible.

* **Ruta:** `/healthCheck`
* **Descripción:** Este método permite validar si el servicio está disponible.
* **Ejemplo de Uso:**

 ```
curl -X 'GET' \
  'http://localhost:8081/healthCheck' \
  -H 'accept: */*'
 ```

### Código de estado HTTP

* **200:** Description: Service on

* **500 Internal Server Error:** Error interno en el servidor.

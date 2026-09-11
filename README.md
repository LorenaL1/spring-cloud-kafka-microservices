# Products Command Microservice

## Descripción

Este proyecto es un microservicio desarrollado con **Spring Boot**, **Spring Cloud** y **Apache Kafka**. Su propósito es gestionar comandos relacionados con productos, como la creación, actualización y eliminación, y comunicarse con otros microservicios a través de mensajería basada en Kafka.

## Tecnologías utilizadas

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Cloud 2025.1.3**
- **Apache Kafka**
- **MySQL** (como base de datos)
- **Maven** (para la gestión de dependencias)

## Dependencias principales

- `spring-boot-starter-web`: Para construir APIs REST.
- `spring-boot-starter-data-jpa`: Para la persistencia de datos con JPA/Hibernate.
- `spring-cloud-starter-stream-kafka`: Para la integración con Apache Kafka.
- `spring-boot-starter-actuator`: Para monitoreo y métricas.
- `mysql-connector-j`: Para la conexión con la base de datos MySQL.
- `spring-boot-starter-test`: Para pruebas unitarias y de integración.

## Configuración

### Prerrequisitos

- **Java 21** instalado.
- **Apache Kafka** configurado y en ejecución.
- **MySQL** configurado con una base de datos para este microservicio.
- **Maven** instalado para la construcción del proyecto.

### Variables de entorno

Configura las siguientes variables de entorno para conectar el microservicio:

- `SPRING_DATASOURCE_URL`: URL de la base de datos MySQL.
- `SPRING_DATASOURCE_USERNAME`: Usuario de la base de datos.
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la base de datos.
- `KAFKA_BOOTSTRAP_SERVERS`: Dirección del servidor Kafka.

### Ejecución

1. Clona este repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd products-command

   
2. Clona este repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd products-api

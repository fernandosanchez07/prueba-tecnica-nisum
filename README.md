# Prubea Tecnica NISUM

## Consideraciones generales:

Se realizo la prueba para el registro de usuario con los siguientes parametros tecnicos:
- Java 21
- SpringBoot 3.3.2
- Spring Security
- Gradle
- Arquitectura hexagonal
- JUnit
- Mockito
- Hibernate
- JPA.
  
Se tuvo en cuenta conceptos de clean code, patrones de diseño y principios solid.

### Base de datos
Como base de datos se utilizo la base de datos en memoria H2.
No se le asignaron valores de password.
Los scripts se encuentran en la api en la seccion de **resources** con el nombre de **schema.sql**.

Para acceder a su panel se puede hacer a travez de la siguiente URL:
- [BASE DE DATOS USUARIOS](http://localhost:8000/h2)

### Swagger
Se implemento swagger para documentar la api y sus endpoints.
A este se puede acceder en la siguiente URL:
- [SWAGGER USUARIOS](http://localhost:8000/doc/swagger-ui/index.html)

## Contexto de funcionamiento
En los archivos del proyecto se encuentra un arhcivo con nombre **endpoints-prueba-tecnica-nisum.json** exportado desde insomnia para que los endpoinst se puedan probar mas facilmente.
La api se ejecuta en el puerto 8000 pero se puede cambiar en el application.yaml, implica ajustar los endpoints del archivo.
Tiene 3 endpoints principales los cuales se pueden ejecutar desde insomnia o postman:

1. Registro de usuario: **POST** http://localhost:8000/auth/register
   - Este endpoint permite registrar el usuario segun el formato indicado y retornando la informacion solicitada.
   - El endpoint no cuenta con seguridad por ser el endpoint de inicio.
   - En caso de que se intente registrar dos veces el mismo usuario se retorna un mensaje segun las indicaciones de la prueba.
2. Logueo de usuarios: **POST** http://localhost:8000/auth/login
   - Este endpoint tampoco cuenta con seguridad ya que este tambien tiene la capacidad de retornar el JWT.
   - Este endpoint permite logguear un usuario con su email y password.
   - Este enpoint retorna el id del usuario registrado y su JWT para poder consumir el ultimo endpoint. 
4. Actualizacion de usuario. **PUT** http://localhost:8000/user/update
   - Este endpoint permite actualizar el nombre del usuario con el id y el JWT retornado durante el loggueo o registro.
   - Este endpoint no permite ser consumido si no se le envia el JWT en la peticion.
   - Debe colocarse el insomnia o postman en Bearer token.
 ## Consideraciones
- Implemente una maquetacion basada en arquitectura hexagonal para serparar la logica de negocio de otras logicas del proyecto.
  Con esto se buscar respetar el principio de responsabilidad unica, hacemos uso de inyeccion de dependencia e inversion de depencias,
  hago uso del patron adaptador, el patron builde, entre otros posible patrones que en un proyecto mas robusto se podrian implementar.
- Se agrego una secretKey para la validacion del token, esta key se encuentra en el application.yaml, aunque se puede haber extendido a variables de entorno pero no se hizo para que la prueba se pueda testear mas facilmente.
- El patron del password actualmente se encuentra ingualmente en el application.yaml para que pueda se configurable mas facilmente o extenderse a variables de entorno del proyecto,  en este el patron indica indica que se la password debe tener al menos un caracter numerico.
- Se realizarion pruebas unitarias a diferentes partes del proyecto.

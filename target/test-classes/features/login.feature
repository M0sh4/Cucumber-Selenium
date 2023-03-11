#language: es
@LOGIN_REGRESION
Característica: Login
  Yo, como usuario
  Quiero tener una opcion para iniciar sesion
  Para ver todos los items
  @CASO1 @HAPPY_PATH
  Escenario: caso1 - Iniciar Sesion
    Dado que me encuentro en la pagina de login de SauceDemo
    Cuando inicio sesion con las credenciales usuario: "standard_user", contraseña: "secret_sauce"
    Entonces valido que deberia aparecer el titulo de "Products"
    Y valido que al menos exista 1 item

  @CASO1 @UNHAPPY_PATH
  Escenario: caso1 - Iniciar Sesion con usuario incorrecto
    Dado que me encuentro en la pagina de login de SauceDemo
    Cuando inicio sesion con las credenciales usuario: "locked_out_user", contraseña: "secret_sauce"
    Entonces valido que deberia aparecer el titulo de "Products"
    Y valido que al menos exista 1 item
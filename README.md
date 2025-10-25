# Threadly

**Threadly** es un foro de discusión por consola desarrollado en Java, aplicando **Programación Orientada a Objetos (POO)**, herencia, interfaces, colecciones y buenas prácticas como **DRY** y **SRP**.  

> ⚠️ Nota: Todos los nombres de clases, métodos y atributos están en **inglés**, mientras que los mensajes de error y validaciones se muestran en **español** para guia visual al estar desarrollando ya que el ingles no es mi idioma principal.

---

## Estructura del proyecto

- **model**: entidades principales (`User`, `Post`, `Comment`, `Category`)  
- **model.users**: subpaquete para usuarios específicos (`Admin`, `Member`)  
- **repository**: almacenamiento en memoria (`UserRepository`, `PostRepository`, `CommentRepository`)  
- **service**: lógica de negocio (`UserService`, `PostService`, `CommentService`, `ForumService`)  
- **util.validators**: validaciones de datos (`BaseValidator`, `UserValidator`, `PostValidator`, `CommentValidator`)  
- **util.constants**: constantes y nombres de campos (`Constant`, `FieldName`)  

---

## Funcionalidades principales

### Usuarios
- Registro de **miembros** y **administradores**.  
- Activación y desactivación de cuentas.  
- Listado de usuarios activos y búsqueda por UUID o email.  

### Posts
- Crear, editar y listar posts por usuario.  
- Cada post tiene título, contenido, categoría (`Category`), autor, fecha de creación y actualización.  
- Validaciones de título, contenido, autor activo y fechas.

### Comentarios
- Agregar y editar comentarios en posts existentes.  
- Cada comentario tiene contenido, autor, post asociado, fecha de creación y actualización.  
- Validaciones de autor activo, contenido y post existente.

### Administrador
- Puede **eliminar posts y comentarios**.  
- Puede **banear y reactivar usuarios**.

### ForumService
- Servicio central que coordina todas las operaciones entre usuarios, posts y comentarios.  
- Facilita la interacción por consola.

---

## Uso por consola

1. Clonar el proyecto y abrir en un IDE compatible con Java y Lombok.  
2. Ejecutar `Main.java` para simular:
   - Registro de usuarios  
   - Creación de posts y comentarios  
   - Acciones de administrador (banear/reactivar usuarios, eliminar posts/comentarios)  

---

## Validaciones y errores

- Mensajes de error en **español**, ejemplos:
  - `"El usuario ya existe."`  
  - `"Post no encontrado."`  
  - `"La cuenta se encuentra desactivada."`  
  - `"El comentario no puede estar vacío."`  

- Validaciones aplicadas a:
  - UUID, username, email  
  - Fechas de registro, creación y actualización  
  - Contenido de posts y comentarios  
  - Usuarios activos para realizar acciones

---

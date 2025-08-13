# Challenge LiterAlura - Oracle ONE Program

Catálogo de libros interactivo donde puedes registrar libros en una base de datos y obtener información sobre ellos.  Aprenderás a realizar solicitudes a una API de libros, a manipular datos JSON, guardarlos en una base de datos y, finalmente, a filtrar y mostrar los libros y autores de interés.

## Descripción del Proyecto :scroll:

Caso planteado: 

Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscarán a través de una API específica. 

## Sobre el proyecto 🚀

### ✨Requisitos Funcionales del Challenge LiterAlura ✨

👌 1. 🔍 Buscar libro por título (API Gutendex)

- La aplicación debe permitir ingresar el nombre de un libro.
- Consultar la API Gutendex para obtener información del libro.
- Registrar el libro en la base de datos si se encuentra.
- Mostrar los siguientes datos: Título, Autor/a (formato: apellido, nombre), Idioma (EN, ES, PT, FR), Número de descargas.
- Si el libro no existe, mostrar un mensaje claro: “Libro no encontrado”.
- Evitar duplicados: no permitir registrar el mismo libro más de una vez.

👌 2. 📚 Listar libros registrados

- Mostrar todos los libros que ya fueron guardados en la base de datos.

👌 3. 👤 Listar autores registrados

- Mostrar todos los autores únicos que aparecen en los libros registrados.

👌 4. 🧓 Listar autores vivos en un año específico

- Permitir ingresar un año (ej. 1600).
- Mostrar autores cuya fecha de nacimiento y fallecimiento indiquen que estaban vivos en ese año.

👌 5. 🌍 Listar libros por idioma

 - Permitir ingresar un código de idioma (ES, EN, FR, PT).
 - Mostrar todos los libros registrados en ese idioma.

### ⚙️ Requisitos Técnicos ⚙️

🔧 Tecnologías
- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL

📚 API externa: API: Gutendex
- Basada en el Proyecto Gutenberg (más de 70,000 libros gratuitos)
- Leer la documentación para entender cómo obtener datos como: Título, Autor, Idioma, Fecha de nacimiento y fallecimiento del autor



## Visuales :mage_woman:

Al iniciar o clonar, configurar usuario, clave, host y nombre de la bd:

<img width="693" height="263" alt="Captura de pantalla 2025-08-13 114306" src="https://github.com/user-attachments/assets/232f82d7-d354-4950-b1b2-0328b3b0de97" />


Buscando por libro: 

<img width="948" height="839" alt="Captura de pantalla 2025-08-13 112728" src="https://github.com/user-attachments/assets/55dc56be-9957-44d2-8824-df74de32da23" />


Al buscar, se regista en base de datos: 

<img width="621" height="79" alt="Captura de pantalla 2025-08-13 112744" src="https://github.com/user-attachments/assets/88355d39-7077-450f-8614-52a77da2012a" />

<img width="707" height="111" alt="Captura de pantalla 2025-08-13 112830" src="https://github.com/user-attachments/assets/c45ad718-677b-4421-a8d9-32384f16076c" />


Listar libros registrados: 

<img width="876" height="110" alt="Captura de pantalla 2025-08-13 112951" src="https://github.com/user-attachments/assets/333ae5d6-faf9-428a-a7b2-2f2858163194" />


Listar autores registrados: 

<img width="839" height="53" alt="Captura de pantalla 2025-08-13 113004" src="https://github.com/user-attachments/assets/f782f405-c3ef-4df4-be95-fbcc247d6d8d" />


Opción 4 buscando autor vivo según año: 

<img width="718" height="604" alt="Captura de pantalla 2025-08-13 113046" src="https://github.com/user-attachments/assets/f6532769-f99e-4079-b3f0-ff974f2e1e88" />

Aviso de libro no encontrado: 

<img width="521" height="561" alt="Captura de pantalla 2025-08-13 113140" src="https://github.com/user-attachments/assets/c119a3ac-4cab-4f6e-b844-d8b2bafc245f" />



## Autora ⚡ 

- **Andrea Rosero** ⚡  - [Andrea Rosero](https://github.com/andreaendigital)


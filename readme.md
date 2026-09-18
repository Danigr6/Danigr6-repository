# MazeSolver — Proyecto Final 1º DAM

Aplicación de consola en **Java** desarrollada para la gestión de usuarios y la resolución automatizada de laberintos mediante algoritmos de búsqueda en grafos y matrices bidimensionales.

---

## Descripción del Proyecto

**MazeSolver** es una solución de software orientada a la carga, representación bidimensional y resolución de laberintos. El sistema gestiona autenticación de usuarios con persistencia en base de datos relacional y ofrece dos algoritmos de búsqueda para hallar rutas de escape:

1. **Búsqueda en Profundidad (DFS):** Encuentra la primera vía de salida disponible (algoritmo `longPath`).
2. **Búsqueda en Anchura (BFS):** Garantiza matemáticamente el camino óptimo con el menor número de pasos (algoritmo `shortPath`).

---

## Tecnologías y Herramientas

* **Lenguaje principal:** Java (JDK 17+)
* **Base de datos:** MySQL (Gestión de usuarios y autenticación)
* **Conectividad:** JDBC (Java Database Connectivity) con `PreparedStatement`
* **Criptografía:** Hash MD5 para la seguridad de contraseñas
* **Control de versiones:** Git & GitHub
* **Documentación y Modelado:** UML (Casos de uso y Diagrama de Clases)

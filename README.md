# Proyecto3_1910114_1811100
- Nombres: Stefano Casale, Enyerber Silva
- Carnets: 1910114, 1811100
- Universidad Simón Bolívar
- Trimestre Ene - Mar 2026

# Pasos de Ejecucion
1. Instalar Kotlin:
    - Desde tu terminal ejecuta el siguiente comando:
        - sudo apt install kotlin

2. Ubica tu entorno:
    - Desde tu terminal ejecuta el siguiente comando:
        - cd ubicacion/de/tu/entorno

3. Compila el archivo "AlfonsoJose.kt":
    - Desde tu terminal ejecuta el siguiente comando:
        - kotlinc AlfonsoJose.kt -include-runtime -d AlfonsoJose.jar

4. Ejecuta el archivo para calcular el volumen de agua:
    - Asegúrate de tener el archivo de entrada "atlantis.txt" en el mismo directorio.
    - Desde tu terminal ejecuta el siguiente comando:
        - java -jar AlfonsoJose.jar

Al compilar se crea un archivo "AlfonsoJose.jar" en tu entorno que contiene el programa empaquetado y listo para correr.

# Funcionamiento
El programa calcula la cantidad de agua que puede quedar atrapada en la Ciudad Perdida de la Atlántida del juego Mundo Cubo. Dado un archivo de texto (atlantis.txt) que contiene una matriz de dígitos representando las alturas de las torres, el programa:

1. Lee y valida el archivo de texto: Se asegura de que el archivo exista y filtra cualquier línea en blanco extra para evitar errores de lectura.
2. Construye una matriz bidimensional: Mapea cada dígito del texto a un entero, creando una cuadrícula de tamaño n x m.
3. Aplica un algoritmo de expansión: Simula cómo el agua llena los espacios vacíos desde los bordes del mapa hacia el centro, respetando las leyes de la gravedad y los puntos de fuga.
4. Imprime el resultado: Muestra por salida estándar un número entero con la cantidad total de cubos de agua que la ciudad es capaz de retener sin que se desborde.

# Decisiones de Implementacion
Para el manejo de la lectura de archivos, implementamos filtros (`filter { it.isNotBlank() }`) y limpieza de espacios (`trim()`). Esto robustece el programa haciéndolo resistente a formatos inesperados o saltos de línea vacíos al final del archivo `atlantis.txt`.

Para resolver el problema, decidimos implementar el algoritmo óptimo de "Agua atrapada en 3D" (Trapping Rain Water 3D) utilizando una Cola de Prioridad (`PriorityQueue`) y BFS. 

Para la construcción y recorrido:
- Comenzamos evaluando los bordes del mapa. Todos los bordes actúan como nuestra "frontera" inicial y punto de fuga, por lo que se añaden primero a la cola de prioridad.
- Utilizamos la `PriorityQueue` para procesar siempre la torre o el nivel de agua más bajo disponible en nuestra frontera. Esto nos asegura que el agua calculada para las torres interiores nunca supere el punto de fuga más bajo que las conecta con el exterior.
- Creamos un `data class NodoTorre` que implementa la interfaz `Comparable`. Sobrescribimos la función `compareTo` para que la cola de prioridad ordene los elementos estrictamente según su altura (de menor a mayor).
- Implementamos una matriz booleana `visitado` del mismo tamaño que la ciudad para llevar el registro de las celdas ya procesadas y evitar ciclos infinitos.
- Para explorar los vecinos de cada torre, utilizamos arreglos de desplazamiento (`deltaFila` y `deltaCol`), lo que permite revisar las celdas ortogonales (arriba, abajo, izquierda, derecha) de forma limpia dentro de un bucle `for`, evitando la repetición excesiva de condicionales.

A medida que el algoritmo avanza hacia adentro, si encuentra un vecino no visitado con una altura menor a la de la frontera actual, sabemos con certeza que ese vecino actuará como un "tazón" y retendrá agua. La diferencia de alturas se suma a nuestro acumulador total.

# Tabla de complejidad computacional (Big O)

| Método | Complejidad | Descripción |
|---|---|---|
| main() | O(n * m) | Lee el archivo y construye la matriz de n filas por m columnas. Las operaciones de mapeo son lineales proporcionales al tamaño del texto. |
| calcularAguaAtrapada() | O(n * m * log(n * m)) | En el peor caso, cada una de las n*m torres se inserta y se extrae de la cola de prioridad. Las operaciones de la PQ toman un tiempo logarítmico respecto a su tamaño máximo. |
| compareTo() | O(1) | Compara las alturas de dos nodos `NodoTorre` en tiempo constante para mantener ordenada la cola de prioridad. |

Leyenda: 
- n = número de filas de la matriz de la ciudad.
- m = número de columnas de la matriz de la ciudad.
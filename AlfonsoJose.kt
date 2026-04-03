/**
 * Representamos cada torre de la ciudad como un nodo.
 * Implementamos Comparable para que nuestra cola de prioridad procese primero
 * los puntos de escape más bajos.
 */
data class NodoTorre(val fila: Int, val col: Int, val altura: Int) : Comparable<NodoTorre> {
    override fun compareTo(other: NodoTorre): Int = this.altura.compareTo(other.altura)
}

class AlfonsoJose {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val archivo = java.io.File("atlantis.txt")

            // Validamos que el archivo exista antes de intentar leerlo
            if (!archivo.exists()) return

            val lineas = archivo.readLines().filter { it.isNotBlank() }

            // Validamos que no este vacío
            if (lineas.isEmpty()) return

            val n = lineas.size
            val m = lineas[0].trim().length
            
            // Creamos la matriz asegurándonos de limpiar espacios en blanco
            val matriz = Array(n) { i -> 
                lineas[i].trim().map { it.toString().toInt() }.toIntArray()
            }

            println(calcularAguaAtrapada(matriz, n, m))
        }

        /**
         * Calculamos el agua atrapada usando una estrategia de expansión
         * desde los bordes hacia el centro
         */
        private fun calcularAguaAtrapada(matriz: Array<IntArray>, n: Int, m: Int): Int {
            val visitado = Array(n) { BooleanArray(m) }
            val frontera = java.util.PriorityQueue<NodoTorre>()

            // Agregamos los bordes como nuestra frontera inicial de escape
            for (i in 0 until n) {
                for (j in 0 until m) {
                    // Agregamos el agua solo en los bordes
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        frontera.add(NodoTorre(i, j, matriz[i][j]))
                        visitado[i][j] = true
                    }
                }
            }

            var totalCubosAgua = 0
            val deltaFila = intArrayOf(0, 0, 1, -1)
            val deltaCol = intArrayOf(1, -1, 0, 0)

            // Procesamos la frontera mientras queden torres por explorar
            while (frontera.isNotEmpty()) {
                val actual = frontera.poll()
                
                // Exploramos los vecinos del nodo actual
                for (i in 0 until 4) {
                    val nuevaFila = actual.fila + deltaFila[i]
                    val nuevaCol = actual.col + deltaCol[i]

                    // Si el vecino está dentro de la ciudad y no ha sido visitado
                    if (nuevaFila in 0 until n && nuevaCol in 0 until m && !visitado[nuevaFila][nuevaCol]) {
                        visitado[nuevaFila][nuevaCol] = true

                        // Si el vecino es más bajo que nuestra frontera actual, atrapamos agua
                        if (matriz[nuevaFila][nuevaCol] < actual.altura) {
                            totalCubosAgua += actual.altura - matriz[nuevaFila][nuevaCol]
                            // El agua llena el espacio, así que la nueva altura de la frontera es la del agua
                            frontera.add(NodoTorre(nuevaFila, nuevaCol, actual.altura))
                        } else {
                            // Si la torre es más alta, ella define el nuevo nivel de la frontera
                            frontera.add(NodoTorre(nuevaFila, nuevaCol, matriz[nuevaFila][nuevaCol]))
                        }
                    }
                }
            }
            return totalCubosAgua
        }
    }
}

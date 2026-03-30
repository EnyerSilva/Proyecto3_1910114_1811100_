data class NodoTorre(val fila: Int, val col: Int, val altura: Int) : Comparable<NodoTorre> {
    override fun compareTo(other: NodoTorre): Int = this.altura.compareTo(other.altura)
}

class AlfonsoJose {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val archivo = java.io.File("atlantis.txt")

            if (!archivo.exists()) return

            val lineas = archivo.readLines()
            if (lineas.isEmpty()) return

            val n = lineas.size
            val m = lineas[0].length
            
            val matriz = Array(n) { i -> 
                lineas[i].map { it.toString().toInt() }.toIntArray()
            }

            println(calcularAguaAtrapada(matriz, n, m))
        }

        private fun calcularAguaAtrapada(matriz: Array<IntArray>, n: Int, m: Int): Int {
            val visitado = Array(n) { BooleanArray(m) }
            
            val frontera = java.util.PriorityQueue<NodoTorre>()

            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        frontera.add(NodoTorre(i, j, matriz[i][j]))
                        visitado[i][j] = true
                    }
                }
            }

            var totalCubosAgua = 0
            val deltaFila = intArrayOf(0, 0, 1, -1)
            val deltaCol = intArrayOf(1, -1, 0, 0)

            while (frontera.isNotEmpty()) {
                val actual = frontera.poll()
                
                for (i in 0 until 4) {
                    val nuevaFila = actual.fila + deltaFila[i]
                    val nuevaCol = actual.col + deltaCol[i]

                    if (nuevaFila in 0 until n && nuevaCol in 0 until m && !visitado[nuevaFila][nuevaCol]) {
                        visitado[nuevaFila][nuevaCol] = true

                        if (matriz[nuevaFila][nuevaCol] < actual.altura) {
                            totalCubosAgua += actual.altura - matriz[nuevaFila][nuevaCol]
                            frontera.add(NodoTorre(nuevaFila, nuevaCol, actual.altura))
                        } else {
                            frontera.add(NodoTorre(nuevaFila, nuevaCol, matriz[nuevaFila][nuevaCol]))
                        }
                    }
                }
            }
            return totalCubosAgua
        }
    }
}

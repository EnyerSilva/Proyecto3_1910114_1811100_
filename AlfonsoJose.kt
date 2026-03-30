data class NodoTorre(val fila: Int, val col: Int, val altura: Int) : Comparable<NodoTorre> {
    override fun compareTo(other: NodoTorre): Int = this.altura.compareTo(other.altura)
}

class AlfonsoJose{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nombreArchivo = "atlantis.txt"
            val archivo = File(nombreArchivo)

            if (!archivo.exists()) return

            val lineas = archivo.readLines()
            if (lines.isEmpty()) return

            val n = lineas.size
            val m = lines[0].length
            val matriz = Array(n) { i -> lineas[i].map { it.toString().toInt() } .toIntArray()
            }

            println(calcularAguaAtrapada(matriz, n, m))
        }

        

    }
}

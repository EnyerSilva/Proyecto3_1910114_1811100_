data class NodoTorre(val fila: Int, val col: Int, val altura: Int) : Comparable<NodoTorre> {
    override fun compareTo(other: NodoTorre): Int = this.altura.compareTo(other.altura)
}

class AlfonsoJose{
    
}

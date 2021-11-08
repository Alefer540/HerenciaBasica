import kotlin.random.Random

fun main(){

    val guerrero1= Guerrero("pepe")
    val minion1 =Minion("nacho")
    val superjefe=Superjefe("josemicod5")

    println(guerrero1)
    println(superjefe)

    do{
        guerrero1.atacar(1,6,superjefe)
        if(superjefe.estaVivo())
            superjefe.atacar(1,4,guerrero1)
    }while(!guerrero1.estaVivo()||superjefe.estaVivo())

    println(guerrero1)
    println(superjefe)


}

open class Personaje(var nombre:String,var vida : Int){

    fun atacar(valorminimo : Int, valorMaximo : Int,personaje:Personaje) {
        personaje.quitarVida(Random.nextInt(valorminimo, valorMaximo + 1))
    }
     open fun quitarVida(dano:Int){
        vida -= dano
    }
    open fun estaVivo():Boolean{
        return vida > 0

    }

    override fun toString():String{
        return "Soy $nombre y tengo $vida puntos de vida"
    }
}
class Guerrero(alias : String) : Personaje(alias,50){

    fun curarse(){
         if (Random.nextInt(0,100)<5){
             vida += 5
         }
    }
}
class Minion(alias: String):Personaje(alias,10){

}

class Superjefe (alias:String) :Personaje(alias,25){

    val listaMinions = mutableListOf(Minion("ignacio1"),Minion("ignacio2"),Minion("ignacio3"))

    override fun quitarVida(dano: Int) {
        if (listaMinions.isNotEmpty()) {
            listaMinions[0].quitarVida(dano)
            if (!listaMinions[0].estaVivo()){
                listaMinions.removeAt(0)
            }
        }else {
            super.quitarVida(dano)}
    }

    override fun toString():String{
    var output=super.toString()
    listaMinions.forEach{
        output += "\t"+ it.toString()
    }
    return output
}
}




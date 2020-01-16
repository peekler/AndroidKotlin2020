package hu.ecity.helloandroid

class Car(var type: String){

    init {
        type="$type Astra"
    }

    fun myType() : String{
        return type
    }
}
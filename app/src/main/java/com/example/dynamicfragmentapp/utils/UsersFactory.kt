package com.example.dynamicfragmentapp.utils

import com.example.dynamicfragmentapp.models.User

class UsersFactory private constructor(){ //Indicando constructor privado, no dejamos instanciar libremente, lo controlamos dentro de la misma


    companion object{ //Companion indica atributos y métodos estáticos
        private  var sInstance: UsersFactory? = null

        //Método estático que inicializa instancia o devuelva la instanciada anteriormente
        fun getInstance(): UsersFactory{
            //sInstance si no es null, llama al constuctor
            sInstance = sInstance ?: UsersFactory()
            return sInstance!!
        }
    }

    private var userList: MutableList<User>? = null
    init {//Bloque de código que se ejecuta luego del constructor
        userList = ArrayList()
        addUsers()
    }

    private fun addUsers(){
        val user1 = User(0, "Juan", "juan.com", "4143-1243")
        val user2 = User(1, "Martina", "martina.com", "4555-1555")
        val user3 = User(2, "José", "jose.com", "4666-1666")

        userList!!.add(user1)
        userList!!.add(user2)
        userList!!.add(user3)
    }

    fun getAllUsers(): List<User>{
        return userList!!
    }

    fun getUserById(id: Int): User{
        return userList!![id]
    }
}
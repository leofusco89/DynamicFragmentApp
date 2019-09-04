package com.example.dynamicfragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), UsersListFragment.listenerItemSeleccionado {

    override fun metodoItemSeleccionado(userId: Int) {
        val userDetailFragment = UserDetailFragment.newInstance(userId) //Creamos una instacia del fragment detalle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_user_list, userDetailFragment) //Usamos el replace para reemplazar el contenido de lo que
            //haya en el contenedor FL_USER_LIST en vez de agregar el frament encima, así ocupa menos memoria
            .addToBackStack(null) //Agregamos el fragment al stack, así al volver, no sale de la app,
            //sino que vuelve a cargar el anterior, ejecuta su ciclo post onCreate, no ejecuta el onCreate
            //pero sí el resto del ciclo. Recibe un texto que identifica la transacción, pero no lo vamos a usar
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userListFragment = UsersListFragment()
        //Con un supportFragmentManager vamos a poder realizar
        //acciones en los fragmentos y/o FrameLayout
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //Realizamos transacción add que agrega una instancia de una clase que herede de Fragment
        //en un FrameLayout
        fragmentTransaction.add(R.id.fl_user_list, userListFragment)
        //Gatillamos transacción cargada
        fragmentTransaction.commit()
    }
}

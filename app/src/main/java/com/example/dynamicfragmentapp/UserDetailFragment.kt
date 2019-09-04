package com.example.dynamicfragmentapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dynamicfragmentapp.utils.UsersFactory
import kotlinx.android.synthetic.main.fragment_user_detail.*

class UserDetailFragment: Fragment() {

    companion object{
        private const val USER_ID = "USER_ID" //const obliga a dar valor hardcodeado, solo puede usarse en companion o por fuera de la clase

        fun newInstance(userId: Int): UserDetailFragment { //Llamado newInstance por convención
            //Recibe por parámetro el número de usuario y vamos a devolver una instancia
            //con el dato de usuario

            val userDetailFragment = UserDetailFragment()
            //Creamos un argumento/bundle, que es un paquete de datos y que sirve para que no se
            //pierdan datos cuando se destruya el fragment, por ejemplo, para cuando vemos otra
            //app y volvemos a la nuestra, debemos volver a cargarla y necesitamos saber que
            //usuario cargar:
            val args = Bundle()
            args.putInt(USER_ID, userId)

            //Le pasamos el argumento a la instancia del fragment para que no se pierda ese dato
            userDetailFragment.arguments = args
            return userDetailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Acá vamos a recuperar el valor del usuario para asignarlo en el TextView
        val factory = UsersFactory.getInstance()
        val userId = arguments!!.getInt(USER_ID)
        val user = factory.getUserById(userId)
        tv_user_name_detail.text = user.name
    }
}
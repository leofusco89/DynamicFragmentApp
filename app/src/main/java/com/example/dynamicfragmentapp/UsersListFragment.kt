package com.example.dynamicfragmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dynamicfragmentapp.models.User
import com.example.dynamicfragmentapp.utils.UsersFactory
import kotlinx.android.synthetic.main.fragment_user_list.*
import java.lang.ClassCastException

class UsersListFragment : Fragment() {
    private lateinit var users: List<User>
    private lateinit var adapter: UsersAdapter
    private lateinit var listener: listenerItemSeleccionado

    interface listenerItemSeleccionado {
        fun metodoItemSeleccionado(userId: Int)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Guardamos referencia al activity que va a utilizar una instancia de este fragmento
        //Lo hacemos dentro de try-catch por las dudas que el desarrollador se olvide de
        //implementar la interfaz en la activity al momento de querer usar este fragment, solo
        //como aviso
        try {
            //Entre todas las cosas que tiene el contexto, nos quedamos con la referencia a todos
            //los métodos de la interfaz porque es lo único que nos interesa
            listener = context as listenerItemSeleccionado
        } catch (ex: ClassCastException) {
            throw ClassCastException("Hay que implementar interfaz listenerItemSeleccionado en la activity que llama este fragmento")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Obtenemos los datos del listado a mostrar mediante el singleton UsersFactory
        val factory = UsersFactory.getInstance()
        users = factory.getAllUsers()
        adapter = UsersAdapter(users)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //En este método indicamos el layout que se usará
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Ejecutado al momento que se crea una actividad que usa este fragmento
        lv_user_list.adapter = adapter

        lv_user_list.setOnItemClickListener { _, _, _, id ->
            listener.metodoItemSeleccionado(id.toInt())
        }
    }
}




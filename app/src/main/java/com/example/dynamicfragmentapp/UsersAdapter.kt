package com.example.dynamicfragmentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dynamicfragmentapp.models.User

class UsersAdapter( private var users: List<User>): BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val vista = p1 ?: LayoutInflater.from(p2!!.context).inflate(R.layout.item_user, p2, false)

        var tvUserName = vista.findViewById<TextView>(R.id.tv_user_name)
        var tvUserWebsite = vista.findViewById<TextView>(R.id.tv_user_website)
        tvUserName.text = getItem(p0).name
        tvUserWebsite.text = getItem(p0).website
        return vista
    }

    override fun getItem(p0: Int): User {
        return users[p0]
    }

    override fun getItemId(p0: Int): Long {
        return users[p0].id.toLong()
    }

    override fun getCount(): Int {
        return users.size
    }
}
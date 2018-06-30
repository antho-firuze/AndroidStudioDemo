package com.firuze.ahmad.demo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.firuze.ahmad.demo.R
import com.firuze.ahmad.demo.models.User

/**
 * Created by antho.firuze@gmail.com on 2018-06-20.
 */

class UserAdapter(val userList: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]

        holder.txt_name.text = user.name
        holder.txt_address.text = user.address
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_name = itemView.findViewById(R.id.txt_name) as TextView
        val txt_address = itemView.findViewById(R.id.txt_address) as TextView
    }

}
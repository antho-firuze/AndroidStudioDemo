package com.firuze.ahmad.demo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.firuze.ahmad.demo.R
import com.firuze.ahmad.demo.adapters.UserAdapter
import com.firuze.ahmad.demo.models.User
import kotlinx.android.synthetic.main.recycleview.*

/**
 * Created by antho.firuze@gmail.com on 2018-06-13.
 */
 
class RecycleView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycleview)

        rv_user.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<User>()
        users.add(User("Adam", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Ahmad", "Depok, Indonesia"))
        users.add(User("Zakky", "Depok, Indonesia"))

        rv_user.adapter = UserAdapter(users)
    }
}
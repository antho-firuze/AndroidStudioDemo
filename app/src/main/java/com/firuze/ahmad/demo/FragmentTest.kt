package com.firuze.ahmad.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by antho.firuze@gmail.com on 2018-06-13.
 */
 
class FragmentTest : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.a_fragment, container, false)
    }
}
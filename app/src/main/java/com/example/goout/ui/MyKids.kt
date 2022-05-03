package com.example.goout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goout.R
import com.example.goout.lists.mykidslist.MyKidsAdapter
import com.example.goout.model.Kid

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyKids.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyKids : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var recyclerKid: RecyclerView
    lateinit var recyclerKidadapter: MyKidsAdapter
    lateinit var kidList : MutableList<Kid>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_kids, container, false)
    }


}
package com.example.goout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goout.R
import com.example.goout.`interface`.ParentApiInterface
import com.example.goout.lists.mykidslist.MyKidsAdapter
import com.example.goout.model.Kid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
     var kidList : MutableList<Kid> = emptyList<Kid>().toMutableList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val view = inflater.inflate(R.layout.fragment_my_kids, container, false)
            recyclerKid = view.findViewById(R.id.recyclerview)
            recyclerKidadapter = MyKidsAdapter(kidList)
            recyclerKid.adapter = recyclerKidadapter
            recyclerKid.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        var apiInterface = ParentApiInterface.create()
        apiInterface.getkids("61cc3989fb6c6e821881b542").enqueue(object: Callback<List<Kid>>{
            override fun onResponse(call: Call<List<Kid>>, response: Response<List<Kid>>) {
                kidList.addAll(response.body()!!)
                recyclerKidadapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<Kid>>, t: Throwable) {
                println("couldnt get the array")
                kidList = emptyList<Kid>().toMutableList()

            }

        })
        // Inflate the layout for this fragment
        return view
    }


}
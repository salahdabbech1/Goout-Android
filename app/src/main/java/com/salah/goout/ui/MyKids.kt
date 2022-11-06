package com.salah.goout.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salah.goout.R
import com.salah.goout.`interface`.ParentApiInterface
import com.salah.goout.`interface`.TaskApiInterface
import com.salah.goout.lists.mykidslist.MyKidsAdapter
import com.salah.goout.lists.task.TaskAdapter
import com.salah.goout.model.Kid
import com.salah.goout.model.Task
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
    //Kids recycler view
    lateinit var recyclerKid: RecyclerView
    lateinit var recyclerKidadapter: MyKidsAdapter
    var kidList: MutableList<Kid> = emptyList<Kid>().toMutableList()
    //task recycler view
    lateinit var recyclerTask: RecyclerView
    lateinit var recyclerTaskadapter: TaskAdapter
    var taskList: MutableList<Task> = emptyList<Task>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_kids, container, false)
        recyclerKid = view.findViewById(R.id.recyclerviewkids)
        recyclerKidadapter = MyKidsAdapter(kidList)
        recyclerKid.adapter = recyclerKidadapter
        recyclerKid.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        var apiInterface = ParentApiInterface.create()
        var sharedPreferences = activity?.getSharedPreferences("Login_prefs", Context.MODE_PRIVATE)
        apiInterface.getkids(sharedPreferences?.getString("_id", "nothing in id")!!)
            .enqueue(object : Callback<List<Kid>> {
                override fun onResponse(call: Call<List<Kid>>, response: Response<List<Kid>>) {
                    kidList.addAll(response.body()!!)
                    recyclerKidadapter.notifyDataSetChanged()

                }

                override fun onFailure(call: Call<List<Kid>>, t: Throwable) {
                    println("couldnt get the array")
                    kidList = emptyList<Kid>().toMutableList()

                }

            })
        recyclerTask = view.findViewById(R.id.recyclerviewtasks)
        recyclerTaskadapter = TaskAdapter(taskList)
        recyclerTask.adapter = recyclerTaskadapter
        recyclerTask.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var taskApiInterface = TaskApiInterface.create()
        taskApiInterface.gettask("61cc3c19483423efa1dfc1f2").enqueue(object : Callback<List<Task>>{
            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<List<Task>>, response: Response<List<Task>>) {
                println(response.body())
                taskList.addAll(response.body()!!)

                recyclerTaskadapter.notifyDataSetChanged()
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                println("no tasks")
                taskList = emptyList<Task>().toMutableList()
            }

        })
        // Inflate the layout for this fragment
        return view
    }


}
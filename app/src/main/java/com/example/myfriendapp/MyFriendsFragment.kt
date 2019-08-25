package com.example.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friend_fragment.*
import java.util.*

class MyFriendsFragment:Fragment() {

    private var listTeman: MutableList<MyFriends>? = null

    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDAO? = null

    companion object{
        fun newInstance():MyFriendsFragment{
            return MyFriendsFragment()
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friend_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initLocalDB(){
        db = AppDatabase.getAppDatabase(activity!!)
        myFriendDao = db?.myFriendsDao()
    }

    private fun initView(){
        fabAddfriend.setOnClickListener{
            (activity as MainActivity).tampilMyFriendAddFragment()
        }
//        simulasiDataTeman()
//        tampilTeman()
        ambilDataTeman()
    }

    private fun ambilDataTeman(){
        listTeman = ArrayList()
       myFriendDao?.ambilSemuaTeman()?.observe(this, Observer{ r ->
           listTeman = r as MutableList<MyFriends>?

           when{
               listTeman?.size == 0 ->tampiltoast("Belum ada teman")

               else -> {
                   tampilTeman()
               }
           }
       })
    }

    private fun tampiltoast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    // private fun simulasiDataTeman(){
    //     listTeman = ArrayList()

    //   (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"bambang","L","bambang@gmail.com","086556", "play on"))
    // (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"bambong","L","lol@gmail.com","22222", "lalalala"))
    //}

    private fun tampilTeman(){
        listMyFriend.layoutManager = LinearLayoutManager(activity)
        listMyFriend.adapter = MyFriendAdapter(activity!!, listTeman as ArrayList<MyFriends>)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
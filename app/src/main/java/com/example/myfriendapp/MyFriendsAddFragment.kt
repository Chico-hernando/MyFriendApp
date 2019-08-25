package com.example.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_add_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyFriendsAddFragment :Fragment(){
    companion object{
        fun newInstance():MyFriendsAddFragment{
            return MyFriendsAddFragment()
        }
    }

    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""

    private var db: AppDatabase? = null
    private var myFriendDAO : MyFriendDAO? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.my_friends_add_fragment,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }

    private fun initView(){
        btnSave.setOnClickListener {
            validasiInput()
        }
    }

    private fun initLocalDB (){
        db = AppDatabase.getAppDatabase(activity!!)
        myFriendDAO = db?.myFriendsDao()
    }

    private fun validasiInput(){
        namaInput = editnama.text.toString()
        emailInput = editemail.text.toString()
        telpInput = edittelp.text.toString()
        alamatInput = editalamat.text.toString()
        genderInput = spinnerGender. selectedItem.toString()

        when{
            genderInput.equals("pilih jenis kelamin")-> tampiltoast("tentukan jenis kelamin anda?")
            namaInput.isEmpty()->editnama.error="nama tidak boleh kosong"
            emailInput.isEmpty()->editemail.error="email tidak boleh kosong"
            telpInput.isEmpty()->edittelp.error="Nomor telepon tidak boleh kosong"
            alamatInput.isEmpty()->editalamat.error="alamat tidak boleh kosong"

            else ->{
                val teman = MyFriends(null, namaInput, alamatInput, emailInput, telpInput, genderInput)

                tambahDataTeman(teman)
            }
        }
    }

    private fun tambahDataTeman(teman: MyFriends) : Job {
        return GlobalScope.launch{
            myFriendDAO?.tambahTeman(teman)
            (activity as MainActivity).tampilMyFriendFragment()
        }
    }

    private fun tampiltoast(message: String){
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    // private fun simulasiDataTeman(){
    //     listTeman = ArrayList()

    //   (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"bambang","L","bambang@gmail.com","086556", "play on"))
    // (listTeman as ArrayList<MyFriend>).add(MyFriend(null,"bambong","L","lol@gmail.com","22222", "lalalala"))
    //}

    override fun onDestroy() {
        super.onDestroy()
    }
}
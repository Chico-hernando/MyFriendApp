package com.example.myfriendapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.my_friend_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tampilMyFriendFragment()

    }

    private fun gantifragment(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int){
        val transaction=fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment)
        transaction.commit()
    }
    fun tampilMyFriendFragment(){
        gantifragment(supportFragmentManager, MyFriendsFragment.newInstance(), R.id.contentFrame)
    }
    fun tampilMyFriendAddFragment(){
        gantifragment(supportFragmentManager, MyFriendsAddFragment.newInstance(), R.id.contentFrame)
    }
}

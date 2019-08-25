package com.example.myfriendapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyFriendDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahTeman(friends: MyFriends)

    @Query("SELECT * FROM MyFriends")
    fun ambilSemuaTeman(): LiveData<List<MyFriends>>
}
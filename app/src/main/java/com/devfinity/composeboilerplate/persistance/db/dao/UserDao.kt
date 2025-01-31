package com.devfinity.composeboilerplate.persistance.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.devfinity.composeboilerplate.persistance.db.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: UserEntity)
}
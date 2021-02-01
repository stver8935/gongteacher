package com.study.gongteacher.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.study.gongteacher.data.dto.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT *FROM users")
    List<User> getAll();


    @Query("DELETE from users")
    void dropTable();


    @Insert
    void inserAll(User... users);

    @Delete
    void delete(User user);

}

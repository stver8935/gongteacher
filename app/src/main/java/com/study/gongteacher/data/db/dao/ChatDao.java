package com.study.gongteacher.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.study.gongteacher.data.dto.Chat;

import java.util.List;

@Dao
public interface ChatDao {

    @Query("SELECT *FROM Chat WHERE chatRoomKey = :chatRoomKey order by uploadDate ASC")
    List<Chat> get(int chatRoomKey);

    @Insert
    void insert(Chat chat);

    @Insert
    void insertAll(Chat... chats);

    @Delete
    void delete(Chat chat);

    @Query("DELETE FROM chat")
    void dropTable();


}

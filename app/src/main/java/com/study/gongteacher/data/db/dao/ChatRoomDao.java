package com.study.gongteacher.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.study.gongteacher.data.dto.ChatRoom;

import java.util.List;

@Dao
public interface ChatRoomDao {


    @Query("SELECT * FROM chat_room")
    List<ChatRoom> getAll();

    @Insert
    void insert(ChatRoom chatRoom);

    @Insert
    void insertAll(ChatRoom... chatRooms);

    @Delete
    void delete(ChatRoom chatRoom);

    @Query("DELETE FROM chat_room")
    void dropTable();

}

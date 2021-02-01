package com.study.gongteacher.data.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.study.gongteacher.data.dto.Tutor;

import java.util.List;

@Dao
public interface TutorDao {
    @Query("SELECT *FROM tutors")
    List<Tutor> getAll();

    @Query("DELETE FROM tutors")
    void dropTable();

    @Insert
    void insertAll(Tutor... tutors);

    @Delete
    void delete(Tutor tutor);


}

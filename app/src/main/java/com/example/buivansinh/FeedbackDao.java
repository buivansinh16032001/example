package com.example.buivansinh;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FeedbackDao {
    @Insert(onConflict = REPLACE)
    long insertFeedback(FeedbackEntity item);

    @Query("SELECT * FROM feedback")
    List<FeedbackEntity> getAllItem();

}

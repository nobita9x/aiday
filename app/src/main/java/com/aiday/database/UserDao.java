package com.aiday.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE mobile =:mobile LIMIT 1")
    User findByNumber(String mobile);

    @Insert
    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(User user);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();
}
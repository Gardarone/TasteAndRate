package com.example.tasteandrate.Category;

import java.util.List;
import java.util.Locale;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CategoryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CategoryName categoryName );

    @Query("DELETE FROM category_table")
    void deleteAll();

    @Query("SELECT * from category_table ORDER BY category ASC")
    LiveData<List<CategoryName>> getAlpabetizedWords();

}

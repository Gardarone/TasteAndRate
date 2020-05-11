package com.example.tasteandrate.Category;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CategoryRepository {

    private CategoryDAO mCategoryDao;
    private LiveData<List<CategoryName>> mAllCategorys;

    CategoryRepository(Application application){
        CategoryRoomDatabase db = CategoryRoomDatabase.getDatabase(application);
        mCategoryDao = db.categoryDAO();
        mAllCategorys = (LiveData<List<CategoryName>>) mCategoryDao.getAlpabetizedWords();
    }

    LiveData<List<CategoryName>> getAllCategorys(){
        return mAllCategorys;
    }

    void insert(CategoryName categoryName){
        CategoryRoomDatabase.databaseWriteExecutor.execute(()-> {
            mCategoryDao.insert(categoryName);
        });
    }
}

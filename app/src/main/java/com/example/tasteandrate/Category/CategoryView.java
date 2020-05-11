package com.example.tasteandrate.Category;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CategoryView extends AndroidViewModel {

    private CategoryRepository mRepository;
    private LiveData<List<CategoryName>> mAllCategory;

    public CategoryView (Application application){
        super(application);
        mRepository = new CategoryRepository(application);
        mAllCategory = mRepository.getAllCategorys();
    }

    LiveData<List<CategoryName>> getAllCategory(){
        return mAllCategory;
    }

    public void insert(CategoryName categoryName) {
        mRepository.insert(categoryName);
    }
}

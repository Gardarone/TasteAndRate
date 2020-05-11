package com.example.tasteandrate.Category;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Category_table")
    public class CategoryName {

        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "category")

        private String mCategory;

        public CategoryName(String category) {this.mCategory = category;}

        public String getCategory(){return this.mCategory;}

    }

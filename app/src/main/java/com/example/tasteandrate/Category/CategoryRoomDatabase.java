package com.example.tasteandrate.Category;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {CategoryName.class}, version = 1, exportSchema = false)
public abstract class CategoryRoomDatabase extends RoomDatabase {

    public abstract CategoryDAO categoryDAO();

    public static volatile CategoryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CategoryRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (CategoryRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CategoryRoomDatabase.class, "category_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            databaseWriteExecutor.execute(()-> {
                CategoryDAO dao = INSTANCE.categoryDAO();
                dao.deleteAll();

                CategoryName categoryName = new CategoryName("Pizza");
                dao.insert(categoryName);
            });
        }
    };
}

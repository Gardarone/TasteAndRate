package com.example.tasteandrate.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tasteandrate.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        private final TextView wordItemView;

        private CategoryViewHolder(View itemView){
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<CategoryName> mCategoryName;

    CategoryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = mInflater.inflate(R.layout.recyclerview_category_view, parent, false);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position){
        if(mCategoryName != null){
            CategoryName current = mCategoryName.get(position);
            holder.wordItemView.setText(current.getCategory());
        } else {
            holder.wordItemView.setText("No Word");
        }
    }

    void setCategoryNames(List<CategoryName> categoryNames){
        mCategoryName = categoryNames;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCategoryName != null){
            return mCategoryName.size();

        } else return 0;
    }
}



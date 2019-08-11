package com.lite.application.neworking.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.lite.application.neworking.R;
import com.lite.application.neworking.networkrequest.models.Article;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.FoodHolder> {

    private Context mContext;
    private ArrayList<Article> result;

    public CustomRecyclerAdapter(Context context) {
        this.mContext = context;
        this.result = new ArrayList<>();
    }

    public void clear(){
        this.result.clear();
    }

    public void setData(List<Article> response){
        result.addAll(response);
    }

    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fooditem, parent, false);
        FoodHolder foodHolder = new FoodHolder(view);
        return foodHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        if(result!=null && result.size() > 0) {
            Picasso.with(mContext).load(result.get(position).getUrlToImage())
                    .networkPolicy(NetworkPolicy.OFFLINE).into(holder.mFoodImage);
            holder.mTitle.setText(result.get(position).getTitle());
            holder.mDescription.setText(result.get(position).getDescription());

        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    protected class FoodHolder extends RecyclerView.ViewHolder{

        private ImageView mFoodImage;
        private TextView mTitle;
        private TextView mDescription;
        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            mFoodImage = (ImageView) itemView.findViewById(R.id.foodimage);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDescription = (TextView) itemView.findViewById(R.id.description);
        }
    }
}

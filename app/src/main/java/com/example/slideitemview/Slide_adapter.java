package com.example.slideitemview;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Slide_adapter extends RecyclerView.Adapter<Slide_adapter.SlideViewHolder> {


    ArrayList<SlideItem> slideItemArrayList;
    ViewPager2 viewPager2;

    public Slide_adapter(ArrayList<SlideItem> slideItemArrayList, ViewPager2 viewPager2) {
        this.slideItemArrayList = slideItemArrayList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SlideViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_item_container,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull SlideViewHolder holder, int position) {
        // Here, use modulus to loop the position if it exceeds the size of the list
       // int listPosition = position % slideItemArrayList.size();
        holder.setImage(slideItemArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        // Make item count large enough to simulate an infinite loop
      //  return Integer.MAX_VALUE;
        return slideItemArrayList.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView roundedImageView;
        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            this.roundedImageView=itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SlideItem slideItem){
            roundedImageView.setImageResource(slideItem.getImage());
        }
    }


}

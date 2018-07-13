package com.example.tushar.trial2kriger.Activities.Data;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tushar.trial2kriger.Activities.ImagesListActivity;
import com.example.tushar.trial2kriger.Activities.Model.Images;
import com.example.tushar.trial2kriger.R;
import com.squareup.picasso.*;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Images> imagesList;

    public PostAdapter(Context context, List<Images> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row, parent, false);


        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Images img = imagesList.get(position);
        String imageUrl = null;
        imageUrl = img.getImage();


        //TODO: Use Picasso library to load image
        Picasso.with(context)
                .load(imageUrl)
                .into(holder.image);



    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public Button like;
        public Button comment;
        public Button share;
        String userid;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;

            image = (ImageView) view.findViewById(R.id.postID);
            like=(Button )view.findViewById(R.id.likeID);
            comment=(Button )view.findViewById(R.id.commentID);
            share=(Button )view.findViewById(R.id.ShareID);

like.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(context instanceof ImagesListActivity)
        {
            ((ImagesListActivity)context).likeLogin();
        }
    }
});

            userid = null;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // we can go to the next activity...

                }
            });

        }
    }
}


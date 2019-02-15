package com.unionbankassociation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.unionbankassociation.R;
import com.unionbankassociation.databinding.AdapterPhotoGalleryViewBinding;
import com.unionbankassociation.models.PhotoGalleryData;

import java.util.ArrayList;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<PhotoGalleryData> mNotificationList;
    private View.OnClickListener listener;

    public PhotoGalleryAdapter(Context context, ArrayList<PhotoGalleryData> mNotificationList, View.OnClickListener listener) {
        this.context = context;
        this.mNotificationList = mNotificationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        AdapterPhotoGalleryViewBinding mBinding = AdapterPhotoGalleryViewBinding.inflate(LayoutInflater.from(context), viewGroup, false);
        return new NotificationViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NotificationViewHolder holder = null;
        holder = (NotificationViewHolder) viewHolder;
        holder.bind(mNotificationList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    private class NotificationViewHolder extends RecyclerView.ViewHolder {
        AdapterPhotoGalleryViewBinding mBinding;

        public NotificationViewHolder(AdapterPhotoGalleryViewBinding view) {
            super(view.getRoot());
            mBinding = view;
            mBinding.cardMain.setOnClickListener(listener);
        }

        public void bind(PhotoGalleryData noticData) {
            mBinding.cardMain.setTag(noticData.getImage());
            mBinding.tvName.setText(noticData.getImgName());
            Glide.with(context).asBitmap().load(noticData.getImage()).apply(RequestOptions.placeholderOf(R.drawable.ic_logo)).into(mBinding.ivProduct);
        }
    }
}

package com.unionbankassociation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unionbankassociation.R;
import com.unionbankassociation.models.NotificationResponse;
import com.unionbankassociation.utils.AppUtils;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<NotificationResponse> mNotificationList;
    private View.OnClickListener listener;

    public NotificationAdapter(Context context, ArrayList<NotificationResponse> mNotificationList, View.OnClickListener listener) {
        this.context = context;
        this.mNotificationList = mNotificationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_notification_view, viewGroup, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class NotificationViewHolder extends RecyclerView.ViewHolder {
        public NotificationViewHolder(View view) {
            super(view);

            AppCompatImageView imageView = view.findViewById(R.id.iv_share);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppUtils.share(context, "Under Development", "Union Bank Association", "Share Link");
                }
            });
        }
    }
}

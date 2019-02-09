package com.unionbankassociation.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.NoticeDetailActivity;
import com.unionbankassociation.models.NoticData;
import com.unionbankassociation.utils.AppUtils;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<NoticData> mNotificationList;
    private View.OnClickListener listener;

    public NotificationAdapter(Context context, ArrayList<NoticData> mNotificationList) {
        this.context = context;
        this.mNotificationList = mNotificationList;
//        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_notification_view, viewGroup, false);
        return new NotificationViewHolder(view);
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
        private AppCompatTextView title, tvTime;
        private CardView mainCard;
        AppCompatImageView imageView, ivNotice;

        public NotificationViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.iv_share);
            title = view.findViewById(R.id.title);
            tvTime = view.findViewById(R.id.time);
            mainCard = view.findViewById(R.id.main_card);
            ivNotice = view.findViewById(R.id.iv_notice);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppUtils.share(context, "Under Development", "Union Bank Association", "Share Link");
                }
            });
            mainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NoticeDetailActivity.class);
                    intent.putExtra("DETAILS", mNotificationList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });

        }

        public void bind(NoticData noticData) {
            title.setText(noticData.getTitle());
            tvTime.setText(noticData.getCreatedDateTime());
            Glide.with(context).asBitmap().load(noticData.getImage()).apply(RequestOptions.placeholderOf(R.drawable.ic_logo)).into(ivNotice);

        }
    }
}

package com.unionbankassociation.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.activities.CommonActivityForFragment;
import com.unionbankassociation.adapters.PhotoGalleryAdapter;
import com.unionbankassociation.databinding.LayoutPhotoGalleryBinding;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.PhotoGalleryData;
import com.unionbankassociation.models.PhotoGalleryModel;
import com.unionbankassociation.network.ApiCall;
import com.unionbankassociation.network.ApiInterface;
import com.unionbankassociation.network.RestApi;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.utils.AppUtils;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoGalleryFragment extends Fragment implements NetworkListener, SwipeRefreshLayout.OnRefreshListener {

    private LayoutPhotoGalleryBinding mBinding;
    private ArrayList<PhotoGalleryData> mPhotoList;
    private PhotoGalleryAdapter adapter;
    private int currentPage = 1;
    private boolean isLoading;
    private int currentPageNumber = 1;
    private Activity mActivity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = LayoutPhotoGalleryBinding.inflate(inflater, container, false);
        initView();
        setUpList();
        if (AppUtils.isInternetAvailable(getActivity())) {
            hitPhotoApi(currentPageNumber, true);
        } else {
            AppUtils.showToast(getActivity(), getString(R.string.no_internet));
        }
        return mBinding.getRoot();
    }

    private void hitPhotoApi(int currentPageNumber, boolean isShowProgress) {
        if (mBinding.swipe != null && !mBinding.swipe.isRefreshing() && isShowProgress)
            mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        Call<ResponseBody> call = apiInterface.getPhotoGallery(AppSharedPreference.getInstance().getString(getActivity(), AppSharedPreference.ACCESS_TOKEN), currentPageNumber);
        ApiCall.getInstance().hitService(getActivity(), call, this, 1);


    }

    private void initView() {
        mActivity = getActivity();
//        Fresco.initialize(this);
        mBinding.header.ivMenu.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_back));
        mBinding.header.title.setText(getString(R.string.photo_gallery));
        mBinding.swipe.setOnRefreshListener(this);
        mBinding.header.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CommonActivityForFragment) getActivity()).finish();
            }
        });

    }

    private void setUpList() {
        mPhotoList = new ArrayList<>();
        final GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mBinding.rvPhotoGallery.setLayoutManager(mGridLayoutManager);
        adapter = new PhotoGalleryAdapter(getActivity(), mPhotoList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String) v.getTag();
                showFullImageDialog(url);
            }
        });
        mBinding.rvPhotoGallery.setAdapter(adapter);
        mBinding.rvPhotoGallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = mGridLayoutManager.getChildCount();
                    int totalItems = mGridLayoutManager.getItemCount();
                    int firstVisibleItemPosition = mGridLayoutManager.findFirstVisibleItemPosition();

                    if (isLoading) {
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItems
                                && firstVisibleItemPosition >= 0) {
                            isLoading = false;
                            currentPageNumber++;
                            if (AppUtils.isInternetAvailable(getActivity()))
                                hitPhotoApi(currentPageNumber, false);
                            else
                                AppUtils.showToast(getActivity(), getString(R.string.no_internet));

                        }
                    }
                }

            }
        });
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        PhotoGalleryModel bean = new Gson().fromJson(response, PhotoGalleryModel.class);
        if (bean.CODE == 200) {
            if (mBinding.swipe.isRefreshing()) {
                mBinding.swipe.setRefreshing(false);
                mPhotoList.clear();
            }
            if (bean.getmNotice().getNextpage() > currentPageNumber) {
                isLoading = true;
            } else {
                isLoading = false;
            }
            try {
                mPhotoList.addAll(bean.getmNotice().getNoticeDetails());
            } catch (Exception e) {

            }
            if (mPhotoList.size() > 0) {
                mBinding.rvPhotoGallery.setVisibility(View.VISIBLE);
                mBinding.tvNoData.setVisibility(View.GONE);
            } else {
                mBinding.rvPhotoGallery.setVisibility(View.GONE);
                mBinding.tvNoData.setVisibility(View.VISIBLE);
            }
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        if (AppUtils.isInternetAvailable(getActivity())) {
            currentPageNumber = 1;
            hitPhotoApi(currentPage, false);
        } else {
            AppUtils.showToast(getActivity(), getString(R.string.no_internet));
        }
    }

    private void showFullImageDialog(String url) {
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        final Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_image);
        final AppCompatImageView image = dialog.findViewById(R.id.iv_chat_image);
        ((AppCompatImageView) dialog.findViewById(R.id.iv_cancel_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Glide.with(mActivity).asBitmap().load(url).apply(RequestOptions.placeholderOf(R.drawable.ic_logo).diskCacheStrategy(DiskCacheStrategy.ALL)).listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                image.setImageBitmap(resource);
                return false;
            }
        }).into(image);
        PhotoViewAttacher pAttacher;
        pAttacher = new PhotoViewAttacher(image);
        pAttacher.update();
        dialog.show();
    }


}

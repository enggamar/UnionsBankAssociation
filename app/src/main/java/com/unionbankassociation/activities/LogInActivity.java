package com.unionbankassociation.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.unionbankassociation.R;
import com.unionbankassociation.databinding.ActivityLoginBinding;
import com.unionbankassociation.interfaces.NetworkListener;
import com.unionbankassociation.models.LoginModel;
import com.unionbankassociation.network.ApiCall;
import com.unionbankassociation.network.ApiInterface;
import com.unionbankassociation.network.RestApi;
import com.unionbankassociation.utils.AppConstant;
import com.unionbankassociation.utils.AppSharedPreference;
import com.unionbankassociation.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class LogInActivity extends BaseActivity implements NetworkListener {
    private EditText etEmail, etPassword;
    private Button btLogIn;
    private ProgressBar bar;
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
        setListeners();
    }

    private void setListeners() {
        mBinding.cardLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppUtils.isInternetAvailable(LogInActivity.this)) {
                    if (validate()) {
                        hitLogInAPI();
                    }
//
                } else
                    AppUtils.showToast(LogInActivity.this, getResources().getString(R.string.no_internet));
            }
        });
        mBinding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBinding.textInputEmail.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBinding.etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBinding.textInputPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

    /*
     * Initialize all views IDs
     * */
    private void initView() {

    }

    /*
     * validate all cases
     * */
    private Boolean validate() {
        if (mBinding.etEmail.getText().toString().trim().length() == 0) {
            mBinding.textInputEmail.setErrorEnabled(true);
            mBinding.textInputEmail.setError(getString(R.string.h_username));
            return false;
        } else if (mBinding.etPassword.getText().toString().trim().length() == 0) {
            mBinding.textInputEmail.setErrorEnabled(true);
            mBinding.textInputPassword.setError(getString(R.string.h_password));
            return false;
        }
//        else if (!(mBinding.etEmail.getText().toString().equals("test") && mBinding.etPassword.getText().toString().equals("123456"))) {
//            AppUtils.showToast(this, "Username and password is incorrect");
//            return false;
//        }
        return true;
    }

    /*
     * Hit login Api through email and password
     * */
    private void hitLogInAPI() {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = RestApi.getConnection(ApiInterface.class, AppConstant.BASE_URL);
        final HashMap params = new HashMap<>();
        params.put("user_email", mBinding.etEmail.getText().toString());
        params.put("user_password", mBinding.etPassword.getText().toString());
        params.put("device_id", AppUtils.getDeviceId(LogInActivity.this));
        params.put("device_token", FirebaseInstanceId.getInstance().getToken());
        params.put("device_type", "1");
        Call<ResponseBody> call = apiInterface.login(params);
        ApiCall.getInstance().hitService(LogInActivity.this, call, this, 1);
    }

    @Override
    public void onSuccess(int responseCode, String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        String token = null, refreshToken = null;
        try {
            JSONObject object = new JSONObject(response);
            AppUtils.showToast(LogInActivity.this, object.getString(AppConstant.message));
            LoginModel bean = new Gson().fromJson(response, LoginModel.class);
            int code = object.getInt(AppConstant.code);
            if (code == 200) {
//                token = object.getJSONObject(AppConstant.result).getString(AppConstant.accessToken);
//                refreshToken = object.getJSONObject(AppConstant.result).getString(AppConstant.refreshToken);
                AppSharedPreference.getInstance().putString(LogInActivity.this, AppSharedPreference.ACCESS_TOKEN, bean.getmLoginData().getAccessToken());
//                AppSharedPreference.getInstance().putString(LogInActivity.this, AppSharedPreference.REFRESH_TOKEN, refreshToken);
//                AppSharedPreference.getInstance().putString(LogInActivity.this, AppSharedPreference.PROFILE, response);
                Intent intent = new Intent(LogInActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onError(String response, int requestCode) {
        mBinding.progressBar.setVisibility(View.GONE);
        AppUtils.showToast(LogInActivity.this, response + "");
    }

    @Override
    public void onFailure() {
        mBinding.progressBar.setVisibility(View.GONE);
        AppUtils.showToast(LogInActivity.this, "Failure");
    }
}

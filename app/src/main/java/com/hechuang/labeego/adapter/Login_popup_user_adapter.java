package com.hechuang.labeego.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hechuang.labeego.R;
import com.hechuang.labeego.bean.PhoneLoginBean;

import java.util.List;

public class Login_popup_user_adapter extends BaseQuickAdapter<PhoneLoginBean.DataBean.ListBean, BaseViewHolder> {
    public Login_popup_user_adapter(int layoutResId, @Nullable List<PhoneLoginBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhoneLoginBean.DataBean.ListBean item) {
        Glide.with(mContext).load(item.getAvatarUrl())
                .apply(new RequestOptions().error(R.drawable.avatar_default).centerCrop())
                .into((ImageView) helper.getView(R.id.login_popup_user_icon));
        helper.setText(R.id.login_popup_user_name, item.getUserId());
    }
}

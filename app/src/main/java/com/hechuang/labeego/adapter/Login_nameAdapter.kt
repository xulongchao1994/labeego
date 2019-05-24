package com.hechuang.labeego

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hechuang.labeego.base.RvAdapter
import com.hechuang.labeego.base.RvHolder
import com.hechuang.labeego.base.RvListener
import com.hechuang.labeego.bean.Login_nameBean
import kotlinx.android.synthetic.main.adapter_login_namelist.view.*

/**
 * Created by Android_xu on 2018/3/27.
 */
class Login_nameAdapter(list: List<Login_nameBean>, context: Context, listener: RvListener) : RvAdapter<Login_nameBean>(list, context, listener) {
    override fun getLayoutId(viewtype: Int): Int {
        return R.layout.adapter_login_namelist
    }

    override fun getHolder(view: View?, viewtype: Int): RvHolder<*> {
        return Login_nameViewHolder(view!!, viewtype, lsitener)
    }

    internal inner class Login_nameViewHolder(itemView: View, type: Int, listener: RvListener) : RvHolder<Login_nameBean>(itemView, type, listener) {
        override fun bindHolder(t: Login_nameBean?, position: Int) {
            Glide.with(mContext).load(R.drawable.avatar_default)
                    .apply(RequestOptions().centerCrop())
                    .into(itemView.login_name_icon)
            itemView.login_name_name.text = t!!.name
        }

    }
}
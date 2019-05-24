package com.hechuang.labeego.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hechuang.labeego.R;
import com.hechuang.labeego.bean.Allianceshop_classify_bean;
import com.hechuang.labeego.ui.activity.ShopListActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18.
 */

public class RightRecyclerAdapter extends RecyclerView.Adapter<RightRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Allianceshop_classify_bean.DataBean.ListBean> listdata;
    private RecyclerView recyclerView;
    private RightListener listener;

    public RightRecyclerAdapter(Context context, List<Allianceshop_classify_bean.DataBean.ListBean> listdata, RecyclerView recyclerView) {
        this.context = context;
        this.listdata = listdata;
        this.recyclerView = recyclerView;
    }

    /**
     * 获取被选中的位置，将选中项移动到顶部，并刷新
     *
     * @param selectedPosition
     */
    public void getSelectedPosition(int selectedPosition) {
        //调用移动位置的方法,直接移动到顶部第一个位置
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(selectedPosition, 0);
        //刷新
        notifyDataSetChanged();
    }

    /**
     * RecyclerView的点击方法
     *
     * @param listener
     */
    public void setItemClickListener(RightListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //填充Item中的布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_shopclassify_right_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // 绑定数据
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(new girddaap(R.layout.adapter_shopclassify_rigyt_grid_item, listdata.get(position).getTwo()));
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ViewHolder(View itemView, final RightListener listener) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.adapter_shopclassify_rigty_recycler);
        }
    }

    /**
     * RecyclerView没有内置监听器，自定义item点击事件
     */
    public interface RightListener {

        void onItemClick(int position);
    }

    class girddaap extends BaseQuickAdapter<Allianceshop_classify_bean.DataBean.ListBean.TwoBean, BaseViewHolder> {
        public girddaap(int layoutResId, @Nullable List<Allianceshop_classify_bean.DataBean.ListBean.TwoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Allianceshop_classify_bean.DataBean.ListBean.TwoBean item) {
            helper.setText(R.id.adapter_shopclassify_right_grid_title, item.getName());
            helper.getView(R.id.adapter_shpclassify_right_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopListActivity.class);
                    intent.putExtra("classifyname", item.getName());
                    intent.putExtra("classify", item.getId());
                    context.startActivity(intent);
                }
            });
            helper.getView(R.id.adapter_shopclassify_right_grid_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShopListActivity.class);
                    intent.putExtra("classifyname", item.getName());
                    intent.putExtra("classify", item.getId());
                    context.startActivity(intent);
                }
            });
            TagFlowLayout grid = helper.getView(R.id.adapter_shopclassify_right_grid_guige);
            grid.setAdapter(new TagAdapter<Allianceshop_classify_bean.DataBean.ListBean.TwoBean.ThreeBean>(item.getThree()) {
                @Override
                public View getView(FlowLayout parent, int position, Allianceshop_classify_bean.DataBean.ListBean.TwoBean.ThreeBean threeBean) {
                    TextView textview = (TextView) LayoutInflater.from(context).inflate(R.layout.shopclassify_tv, parent, false);
                    textview.setText(threeBean.getName());
                    return textview;
                }

            });
            grid.setOnTagClickListener((view, position, parent) -> {
                Intent intent = new Intent(context, ShopListActivity.class);
                intent.putExtra("classifyname", item.getThree().get(position).getName());
                intent.putExtra("classify", item.getThree().get(position).getId());
                context.startActivity(intent);
                return false;
            });
        }
    }

}

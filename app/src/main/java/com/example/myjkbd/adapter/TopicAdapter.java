package com.example.myjkbd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myjkbd.R;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: MyJKBD
 * @Package com.example.myjkbd.adapter
 * @Description: todo
 * @author: L-BackPacker
 * @date: 2018.11.05 下午 4:42
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018
 */
public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicVieHolder> {

    private Context mContext;
    private final LayoutInflater inflater;
    /**
     * 当前数据坐标
     */
    private int prePosition;
    /**
     * 用户得数据总数
     */
    private int num;
    /**
     * 自定定义坐标
     */
    private int curPosition;


    public TopicAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public TopicVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_topice, parent, false);
        return new TopicVieHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopicVieHolder holder, final int position) {
        holder.mTvId.setText((position + 1) + "");
        holder.mTvId.setTextColor(Color.parseColor("#b3afaf"));
        holder.mTvId.setBackgroundResource(R.drawable.bg_topic_no);
        if (prePosition == position) {
            holder.mTvId.setBackgroundResource(R.drawable.bg_topic_no);
            holder.mTvId.setTextColor(Color.parseColor("#b3afaf"));
        }
        if (curPosition == position) {
            holder.mTvId.setBackgroundResource(R.drawable.bg_topic_ok);
            holder.mTvId.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.mTvId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(holder, position);
            }
        });
    }

    private OnTopicClickListener listener;

    // 回调接口
    public interface OnTopicClickListener {
        void onClick(TopicVieHolder holder, int position);
    }

    public void setOnTopicClickListener(OnTopicClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return num;
    }

    public void noitfyCurPosition(int curPosition) {
        this.curPosition = curPosition;
        notifyItemChanged(curPosition);
    }

    public void noifyPrePostion(int prePosition) {
        this.prePosition = prePosition;
        notifyItemChanged(prePosition);
    }

    public void setDataNum(int num) {
        this.num = num;
        notifyDataSetChanged();
    }

    public class TopicVieHolder extends RecyclerView.ViewHolder {
        public TextView mTvId;

        public TopicVieHolder(View itemView) {
            super(itemView);
            this.mTvId = (TextView) itemView.findViewById(R.id.tv_id);
        }
    }


}

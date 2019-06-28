package hzyj.come.zhangshangpingtai.copy.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.copy.CommonAdapter;
import hzyj.come.zhangshangpingtai.entity.EntityMessage;

/**
 * Created by EverGlow on 2019/1/5 13:52
 */

public class MessageAdapter extends CommonAdapter<EntityMessage.MessageInfo> {


    Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        bindPosition(viewHolder, position);

    }

    private void bindPosition(ViewHolder viewHolder, int position) {
        EntityMessage.MessageInfo info = mTList.get(position);
        viewHolder.mTvTitle.setText(info.getTitle());
        viewHolder.mTvType.setText(info.getType());
        viewHolder.mTvDate.setText(info.getDate());

        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MessageInfoActivity.class);
            intent.putExtra("id", info.getId());
            mContext.startActivity(intent);
        });


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.rl_message_item)
        LinearLayout mRlMessageItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

package hzyj.come.zhangshangpingtai.copy.commentrecycle;

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
import hzyj.come.zhangshangpingtai.entity.EntityOrderInfo;

/**
 * Created by EverGlow on 2019/1/5 13:52
 */

public class OrderAdapter extends CommonAdapter<EntityOrderInfo.OrderInfo> {


    Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        bindPosition(viewHolder, position);

    }

    private void bindPosition(ViewHolder viewHolder, int position) {
        EntityOrderInfo.OrderInfo orderInfo = mTList.get(position);
        viewHolder.mTvId.setText(String.valueOf(position++));
        viewHolder.mTvCode.setText(orderInfo.getCode());
        viewHolder.mTvUpdatetime.setText(orderInfo.getUpdatetime());
        viewHolder.mTvChargePaid.setText(orderInfo.getChargePaid());
      
        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent =  new Intent(mContext,DetailActivity.class);
            intent.putExtra(DetailActivity.TITLE,"订单");
            intent.putExtra(DetailActivity.TYPE,DetailActivity.ORDER_DETAIL);
            intent.putExtra("code",orderInfo.getCode());
            mContext.startActivity(intent);
        });
            

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_id)
        TextView mTvId;
        @BindView(R.id.tv_code)
        TextView mTvCode;
        @BindView(R.id.tv_updatetime)
        TextView mTvUpdatetime;
        @BindView(R.id.tv_chargePaid)
        TextView mTvChargePaid;
        @BindView(R.id.rl_order_history_item)
        LinearLayout mRlOrderHistoryItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

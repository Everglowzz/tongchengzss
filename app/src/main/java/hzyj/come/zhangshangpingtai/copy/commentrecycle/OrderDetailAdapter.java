package hzyj.come.zhangshangpingtai.copy.commentrecycle;

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
import hzyj.come.zhangshangpingtai.utlis.MyUtils;

/**
 * Created by EverGlow on 2019/1/5 13:52
 */

public class OrderDetailAdapter extends CommonAdapter<EntityOrderInfo.OrderInfo> {


   

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        bindPosition(viewHolder, position);

    }

    private void bindPosition(ViewHolder viewHolder, int position) {

        EntityOrderInfo.OrderInfo info = mTList.get(position);
        viewHolder.mTvName.setText(info.getChargeName());
        viewHolder.mTvBeginTime.setText(MyUtils.getDateToString(info.getStartTime()));
        viewHolder.mEndTime.setText(MyUtils.getDateToString(info.getEndTime()));
        viewHolder.mTvCashier.setText(info.getChargeStaff());
        viewHolder.mTvAmount.setText(info.getChargePaid());
        viewHolder.mTvPrice.setText(info.getChargeUnitPrice());
        viewHolder.mTvCount.setText(info.getNum());


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_begin_time)
        TextView mTvBeginTime;
        @BindView(R.id.end_time)
        TextView mEndTime;
        @BindView(R.id.tv_cashier)
        TextView mTvCashier;
        @BindView(R.id.tv_amount)
        TextView mTvAmount;
        @BindView(R.id.tv_price)
        TextView mTvPrice;
        @BindView(R.id.tv_count)
        TextView mTvCount;
        @BindView(R.id.rl_order_history_item)
        LinearLayout mRlOrderHistoryItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

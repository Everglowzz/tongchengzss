package hzyj.come.zhangshangpingtai.copy.repairs;

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
import hzyj.come.zhangshangpingtai.entity.EntityRepairs;
import hzyj.come.zhangshangpingtai.utlis.MyUtils;

/**
 * Created by EverGlow on 2019/1/5 13:52
 */

public class RepairsHistoryAdapter extends CommonAdapter<EntityRepairs.RepairsInfo> {


    

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repairs_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        bindPosition(viewHolder, position);

    }

    private void bindPosition(ViewHolder viewHolder, int position) {

        EntityRepairs.RepairsInfo info = mTList.get(position);
        if (info == null) {
            return;
        }
        viewHolder.mTvApplyTime.setText(MyUtils.getDateToString(info.getOrderDate()));
        viewHolder.mTvStatus.setText(info.getProcessState());
        if (info.getProcessState().equals("已处理")) {
            viewHolder.mTvAcceptTime.setText(MyUtils.getDateToString(info.getDemandDate()));
        }
        viewHolder.mTvType.setText(info.getDemandType());
        viewHolder.mTvMaterialsPrice.setText(info.getMaterialsCost());
        viewHolder.mTvLabour.setText(info.getLaborCost());
        viewHolder.mTvCause.setText(info.getRemark());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_apply_time)
        TextView mTvApplyTime;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.tv_accept_time)
        TextView mTvAcceptTime;
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_materials_price)
        TextView mTvMaterialsPrice;
        @BindView(R.id.tv_labour)
        TextView mTvLabour;
        @BindView(R.id.tv_cause)
        TextView mTvCause;
        @BindView(R.id.rl_order_history_item)
        LinearLayout mRlOrderHistoryItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

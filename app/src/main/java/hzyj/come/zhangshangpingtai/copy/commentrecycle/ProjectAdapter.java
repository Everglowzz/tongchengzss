package hzyj.come.zhangshangpingtai.copy.commentrecycle;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import hzyj.come.zhangshangpingtai.R;
import hzyj.come.zhangshangpingtai.copy.CommonAdapter;
import hzyj.come.zhangshangpingtai.entity.EntityOrderInfo;

/**
 * Created by EverGlow on 2019/1/5 13:52
 */

public class ProjectAdapter extends CommonAdapter<EntityOrderInfo.OrderInfo> {


    Context mContext;

    HashMap<String, Integer> imges;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        addImages();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ViewHolder(view);
    }

    private void addImages() {
        imges = new HashMap<>();
        imges.put("电费", R.drawable.dian);
        imges.put("物业费", R.drawable.wuye);
        imges.put("水费", R.drawable.shui);
        imges.put("公摊水费", R.drawable.shui);
        imges.put("门禁卡费", R.drawable.ka);
        imges.put("生活垃圾费", R.drawable.laji);
        imges.put("电梯费", R.drawable.dianti);
        imges.put("暖气费", R.drawable.nuanqi);
        imges.put("电损分摊", R.drawable.dian);
        imges.put("装修垃圾费", R.drawable.laji);
        imges.put("水电卡费", R.drawable.ka);
        imges.put("装修电梯费", R.drawable.dianti);
        imges.put("装修押金", R.drawable.yajin);
        imges.put("停车费", R.drawable.tingchefei);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        bindPosition(viewHolder, position);

    }

    private void bindPosition(ViewHolder viewHolder, int position) {
        EntityOrderInfo.OrderInfo orderInfo = mTList.get(position);
        String alias = orderInfo.getAlias();
        viewHolder.mTvName.setText(alias);
        Integer drawable = imges.get(alias);
        if (drawable != null) {
            viewHolder.mIvImage.setImageDrawable(mContext.getResources().getDrawable(drawable));
        }
       
        viewHolder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(DetailActivity.TITLE, alias);
            intent.putExtra(DetailActivity.TYPE, DetailActivity.PROJECT_DETAIL);
            intent.putExtra("id", orderInfo.getId());
            mContext.startActivity(intent);
        });


    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context mContext;
        @BindView(R.id.iv_image)
        ImageView mIvImage;
        @BindView(R.id.tv_name)
        TextView mTvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

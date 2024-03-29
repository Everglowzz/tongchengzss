package hzyj.come.zhangshangpingtai.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;

import hzyj.come.zhangshangpingtai.R;

/**
 * Created by EverGlow on 2018/4/13 10:16
 */
public class GroupViewItem extends AutoRelativeLayout {

    private int mLeftImage;
    private int mRightImage;
    private float mItemHeight;
    private String mItemTitle;
    private String mRightText;
    private boolean mIsShowleft;
    private boolean mIsShowRight;
    private RelativeLayout mRootView;
    private ImageView mIvRight;
    private TextView mTvRight;

    public GroupViewItem(Context context) {
        super(context);
        initView(context);
    }


    public GroupViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs);
        initView(context);
    }


    public GroupViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTypedArray(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.group_view_item, this, true);
        mRootView = findViewById(R.id.layout_root);
        mRootView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) mItemHeight));
        ImageView ivLeft = findViewById(R.id.iv_left);
        mIvRight = findViewById(R.id.iv_right);
        TextView itemTitle = findViewById(R.id.tv_title);
        mTvRight = findViewById(R.id.tv_right_text);
        if (mIsShowleft) {
            ivLeft.setVisibility(VISIBLE);
            ivLeft.setImageResource(mLeftImage);
        } else {
            ivLeft.setVisibility(GONE);
        }
        if (mIsShowRight) {
            mIvRight.setVisibility(VISIBLE);
            mIvRight.setImageResource(mRightImage);
        } else {
            mIvRight.setVisibility(GONE);
        }
        if (!TextUtils.isEmpty(mRightText)) {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(mRightText);
        }
        itemTitle.setText(mItemTitle);

    }

    private void setOnItemClickListener(OnClickListener listener) {
        if (mRootView != null) {
            mRootView.setOnClickListener(listener);
        }

    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GroupViewItem);
        mLeftImage = typedArray.getResourceId(R.styleable.GroupViewItem_leftImage, R.mipmap.ic_launcher);
        mRightImage = typedArray.getResourceId(R.styleable.GroupViewItem_rightImage, R.drawable.arrow_right);
        mItemHeight = typedArray.getDimension(R.styleable.GroupViewItem_itemHeight, 100);
        mItemTitle = typedArray.getString(R.styleable.GroupViewItem_title);
        mRightText = typedArray.getString(R.styleable.GroupViewItem_rightText);
        mIsShowleft = typedArray.getBoolean(R.styleable.GroupViewItem_isShowLeft, true);
        mIsShowRight = typedArray.getBoolean(R.styleable.GroupViewItem_isShowRight, true);

    }

    public void setRightImageClickListener(OnClickListener listener) {
        if (mIvRight != null) {
            mIvRight.setOnClickListener(listener);
        }
    }

    public void setRightImage(Drawable drawable) {
        if (mIvRight != null) {
            mIvRight.setImageDrawable(drawable);
        }
    }
    
    public void setRightText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(text);
        }
    }
}

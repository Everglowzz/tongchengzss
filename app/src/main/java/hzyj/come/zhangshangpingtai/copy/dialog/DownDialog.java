package hzyj.come.zhangshangpingtai.copy.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzyj.come.zhangshangpingtai.R;

/**
 * Created by Youga on 2015/11/6.
 */
public class DownDialog extends BaseDialog {


    boolean manualDismiss;
    @BindView(R.id.update_progressbar)
    NumberProgressBar mUpdateProgressbar;
    @BindView(R.id.btn_houtai)
    Button mBtnHoutai;
    DownDialogCallBack callBack;

    public DownDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        setContentView(R.layout.update_progress_dialog);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
        setCancelable(false);

    }

    @OnClick({R.id.btn_houtai})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_houtai:
                callBack.backgroundUpdate();
                dismiss();
                manualDismiss = true;
                break;
        }
    }

    public void showDialog(int Progress) {
        mUpdateProgressbar.setProgress(Progress);
        if (!isShowing() && !manualDismiss) show();
    }

    public void setDownDialogCallBack(DownDialogCallBack callBack) {
        this.callBack = callBack;
    }

    public interface DownDialogCallBack {
        void backgroundUpdate();
    }
}

package hzyj.come.zhangshangpingtai.copy.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hzyj.come.zhangshangpingtai.R;

/**
 * Created by EverGlow on 2019/1/11 16:07
 */

public class UpdateDialog extends BaseDialog {


    @BindView(R.id.update_context)
    TextView mUpdateContext;
    @BindView(R.id.update_cancel)
    Button mUpdateCancel;
    @BindView(R.id.update_submit)
    Button mUpdateSubmit;
    UpdateDialogCallBack callBack;
    public UpdateDialog(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setContentView(R.layout.update_dialog);
        ButterKnife.bind(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }


    @OnClick({R.id.update_cancel, R.id.update_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_cancel:
                dismiss();
                break;
            case R.id.update_submit:
                callBack.onUpdate();
                dismiss();
                break;
        }
    }

    public void showDialog(String message){
        mUpdateContext.setText(message);
        show();
    }

    public void setUpdateDialogCallBack(UpdateDialogCallBack callBack) {
        this.callBack = callBack;
    }

    public interface UpdateDialogCallBack{
        void onUpdate();
    }

    public Button getCancelButton(){
        return mUpdateCancel;
    }
}

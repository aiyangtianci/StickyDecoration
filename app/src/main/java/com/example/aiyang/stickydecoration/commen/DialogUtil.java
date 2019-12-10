package com.example.aiyang.stickydecoration.commen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.example.aiyang.stickydecoration.R;


/**
 * Created by aiyang on 2018-07-05 19:34:08
 * 兼容2.3版本以上的自定义对话框和进度条
 */

public class DialogUtil {
    /**
     * 拨打电话
     *
     * @param context
     * @param phone
     */
    public static void makeCall(Context context, String phone) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.dialog)
                .setCancelable(false).create();
        if (((Activity) context).isFinishing()) {
            return;
        }
        if (!TextUtils.isEmpty(phone)) {
            alertDialog.show();
        } else {
            showSimpleDialog(context, "提示", "电话号码为空，不可拨打电话!", null);
            return;
        }
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setContentView(R.layout.dialog_make_call);
    }

    /**
     * 单品多规格
     *
     * @param context
     */
    public static Dialog showMultiTagOfDish(Context context, View.OnClickListener listener) {
        final Dialog dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(R.layout.select_multi_tag_dish);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.findViewById(R.id.addtocar).setOnClickListener(listener);
        dialog.findViewById(R.id.iv_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }


    /**
     * 显示基本Dialog
     */
    public static void showSimpleDialog(Context context, String title, String message, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        //监听事件
        if (clickListener != null) {
            builder.setPositiveButton("确认", clickListener);
        } else {
            builder.setNegativeButton("知道了", null);
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    /**
     * 显示基本Dialog
     */
    public static void showDoubleDialog(Activity context, String title, String message, DialogInterface.OnClickListener SubmitListener, DialogInterface.OnClickListener CancerListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        //监听事件
        builder.setPositiveButton("确认", SubmitListener);
        builder.setNegativeButton("取消", CancerListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        if (!context.isFinishing()) {
            alertDialog.show();
        }
    }

    //进度条
    private static Dialog progressDialog;

    /**
     * 显示进度条
     */
    public static void showProgress(Activity context) {
        progressDialog = null;
        progressDialog = new Dialog(context, R.style.loadingdialog);
        progressDialog.setContentView(R.layout.loading_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        if (!context.isFinishing()) {
            progressDialog.show();
        }
    }

    /**
     * 关闭进度条
     */
    public static void dismissProgress() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }

//    /**
//     * 切换环境弹框
//     */
//    private static int clicknum = 0;
//    private static Dialog dialog;
//
//    public static void ChannelOnline(final Activity context) {
//        clicknum = clicknum + 1;
//        if (clicknum < 8) {
//            return;
//        } else {
//            clicknum = 0;
//        }
//        String myBaseUrl = Config.BaseUrl;
//        boolean myDebug = AppUtil.getDebug();
//        if (dialog != null) {
//            if (dialog.isShowing()) {
//                dialog.cancel();
//            }
//            dialog = null;
//        }
//        if (dialog == null) {
//            dialog = new Dialog(context, R.style.dialog);
//            dialog.setContentView(R.layout.dialog_edit);
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setCancelable(false);
//            //提示环境文字
//            final TextView tv_content = (TextView) dialog.findViewById(R.id.tv_content);
//            final TextView currentPath = (TextView) dialog.findViewById(R.id.tv_current_path);
//
//            //切换环境
//            RadioGroup rgp = (RadioGroup) dialog.findViewById(R.id.rgp);
//            rgp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                    switch (checkedId) {
//                        case R.id.rb_one:
//                            Config.BaseUrl = Config.RELEASE_PATH;
//                            tv_content.setText("当前环境：线上");
//                            currentPath.setText(Config.BaseUrl);
//                            break;
//                        case R.id.rb_two:
//                            Config.BaseUrl = Config.TEST_PATH;
//                            tv_content.setText("当前环境：测试");
//                            currentPath.setText(Config.BaseUrl);
//                            break;
//                        case R.id.rb_three:
//                            Config.BaseUrl = Config.DEVELOP_PATH;
//                            tv_content.setText("当前环境：开发");
//                            currentPath.setText(Config.BaseUrl);
//                            break;
//                        case R.id.rb_four:
//                            Config.BaseUrl = Config.PRE_PATH;
//                            tv_content.setText("当前环境：预发");
//                            currentPath.setText(Config.BaseUrl);
//                            break;
//                    }
//                }
//            });
//            //切换加密
//            RadioGroup rgp2 = (RadioGroup) dialog.findViewById(R.id.rgp2);
//            rgp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
//                    if (checkId == R.id.debug_true) {
//                        AppUtil.setDebug(true);
//                    } else if (checkId == R.id.debug_flase) {
//                        AppUtil.setDebug(false);
//                    }
//                }
//            });
//
//            //默认环境
//            RadioButton rb = null;
//            if (TextUtils.equals(Config.BaseUrl, Config.TEST_PATH)) {
//                rb = (RadioButton) dialog.findViewById(R.id.rb_two);
//                rb.setChecked(true);
//            } else if (TextUtils.equals(Config.BaseUrl, Config.RELEASE_PATH)) {
//                rb = (RadioButton) dialog.findViewById(R.id.rb_one);
//                rb.setChecked(true);
//            } else if (TextUtils.equals(Config.BaseUrl, Config.DEVELOP_PATH)) {
//                rb = (RadioButton) dialog.findViewById(R.id.rb_three);
//                rb.setChecked(true);
//            } else if (TextUtils.equals(Config.BaseUrl, Config.PRE_PATH)) {
//                rb = (RadioButton) dialog.findViewById(R.id.rb_four);
//                rb.setChecked(true);
//            }
//            //默认加密
//            RadioButton rb2 = null;
//            if (AppUtil.getDebug()) {
//                rb2 = (RadioButton) dialog.findViewById(R.id.debug_true);
//            } else {
//                rb2 = (RadioButton) dialog.findViewById(R.id.debug_flase);
//            }
//            rb2.setChecked(true);
//            //取消
//            dialog.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                    Config.BaseUrl = myBaseUrl;
//                    AppUtil.setDebug(myDebug);
//                }
//            });
//            //确认
//            final EditText edit = (EditText) dialog.findViewById(R.id.text_edit);
//            dialog.findViewById(R.id.tv_confim).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String password = edit.getText().toString().trim();
//                    if (TextUtils.equals(password, "daojia123")) {
//                        ShowToast.INSTANCE.shortTime("切换成功");
//                        dialog.cancel();
//                        SpUserInfo.clearAccount(context);
//                        RetrofitHelper.getInstance().resetApiService();
//                        AppUtil.restarteApp(context);
//                    } else {
//                        edit.setText("");
//                        ToastUtil.show(context, "密码有误，请重试");
//                    }
//
//                }
//            });
//        }
//
//        dialog.show();
//    }
}

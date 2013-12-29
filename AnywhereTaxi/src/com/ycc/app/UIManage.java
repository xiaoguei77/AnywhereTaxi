package com.ycc.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import com.ycc.anywheretaxi.R;
import com.ycc.app.ui.AppMain;

/**
 * 管理UI之间的跳转等
 * 
 * @author YY
 * 
 */
public class UIManage extends Activity {

	/**
	 * 显示APP主界面
	 * 
	 * @param activity
	 */
	public static void ShowAppMain(Activity activity) {
		Intent intent = new Intent(activity, AppMain.class);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * @param mContext
	 *            上下文
	 * @param msg
	 *            toast文本
	 */
	public static void ShowToast(Context mContext, String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * @param mContext
	 *            上下文
	 * @param msg
	 *            toast文本
	 * @param time
	 *            显示时间
	 */
	public static void ShowToast(Context mContext, String msg, int time) {
		Toast.makeText(mContext, msg, time).show();
	}
	
	/**
	 * 发送App异常崩溃报告
	 * 
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont,
			final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.submit_report,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 发送异常报告
						Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("text/plain"); //模拟器
						//i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,new String[] { "zhoutao_vips@163.com" });
						i.putExtra(Intent.EXTRA_SUBJECT,"美旅Android客户端 - 错误报告");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "发送错误报告"));
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.show();
	}
}

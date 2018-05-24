package connectgames.com.studyspringanimation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * <pre>
 * author :ls
 * time :2018/05/14
 * desc:
 * version :
 * </pre>
 */
public class ScreenTools {
    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWith(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    public static int getHasVirtualKey(Activity activity) {
        int dpi = 0;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 用于计算地图页，每个点的坐标，规定地图标准宽度为1000
     * @param width 原地图尺寸
     * @param realWidth 当前要显示的view的父view 的宽度
     * @param changePoint 要计算的点
     * @return
     */
    public static double getScaleWidth(double width,double realWidth,double changePoint){
        return  realWidth / width * changePoint;
    }

    /**
     * 用于计算地图页，每个点的坐标
     * @param height 原地图尺寸
     * @param realHeight  当前要显示的view的父view 的高度
     * @param changePoint  要计算的点
     * @return
     */
    public static double getScaleHeight(double height,double realHeight, double changePoint){
        return realHeight / height * changePoint;
    }
    /**
     * dp转换成px
     */
    public static int dp2px(Context context,float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * px转换成dp
     */
    public static int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    /**
     * sp转换成px
     */
    public static int sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
    /**
     * px转换成sp
     */
    public static int px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue/fontScale+0.5f);
    }
}

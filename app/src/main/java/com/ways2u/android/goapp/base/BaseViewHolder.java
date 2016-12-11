package com.ways2u.android.goapp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


public class BaseViewHolder {
    /**
     * view存放容器
     */
    private SparseArray<View> mViews;

    /**
     * 当前位置
     */
    private int mPosition;

    /**
     * 复用的view
     */
    private View mConvertView;

    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 布局id
     */
    private int mLayoutId;

    /**
     * 构造器，初始化相关操作
     *
     * @param context  上下文
     * @param parent   父view，填充时使用
     * @param layoutId 布局id
     * @param position 位置
     */
    public BaseViewHolder(Context context, ViewGroup parent, int layoutId,
                          int position) {
        mContext = context;
        mLayoutId = layoutId;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);
    }


    /**
     * 取得一个ViewHolder,如果没有view可以复用则重新创建，否则从复用的view中获取绑定的ViewHolder
     *
     * @param context     上下文
     * @param convertView 复用的view
     * @param parent      复用的view的父view
     * @param layoutId    布局id
     * @param position    当前view的位置
     * @return 返回一个ViewHolder
     */
    /*
    public static BaseViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            BaseViewHolder holder = (BaseViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }
*/
    /**
     * 获取当前view的位置
     *
     * @return 返回当前view的位置
     */
    public int getPosition() {
        return mPosition;
    }

    /**
     * 获取当前view的布局id
     *
     * @return 返回当前view的布局id
     */
    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return 返回对应的view
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取当前复用的view
     *
     * @return 返回当前复用的view
     */
    public View getConvertView() {
        return mConvertView;
    }

    /* ------------------------------------------以下为辅助方法---------------------------------------------*/

    /**
     * 设置TextView的文字
     *
     * @param viewId 要设置的TextView的id
     * @param text   要设置的文字
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 给ImageView设置图片资源
     *
     * @param viewId 要设置的ImageView的id
     * @param resId  要设置的图片的资源id
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * 给ImageView设置位图
     *
     * @param viewId 要设置的ImageView的id
     * @param bitmap 要设置的位图
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 给ImageView设置Drawable
     *
     * @param viewId   要设置的ImageView的id
     * @param drawable 要设置的Drawable
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId 要设置的View的id
     * @param color  要设置的颜色
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置背景资源
     *
     * @param viewId        要设置的View的id
     * @param backgroundRes 要设置的资源
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }
    /**
     * 设置背景
     *
     * @param viewId        要设置的View的id
     * @param drawable 要设置的背景
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackgroundDrawable(drawable);
        return this;
    }

    /**
     * 给TextView设置文字颜色
     *
     * @param viewId    要设置的TextView的id
     * @param textColor 要设置的颜色
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 给TextView设置文字资源颜色
     *
     * @param viewId       要设置的TextView的id
     * @param textColorRes 要设置的颜色资源的id
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * 设置View的透明度
     *
     * @param viewId 要设置的View的id
     * @param value  要设置的透明度值
     * @return 返回当前view的ViewHolder
     */
    @SuppressLint("NewApi")
    public BaseViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * 设置是否可见
     *
     * @param viewId  要设置的view的id
     * @param visible 是否可见，true为可见，false为不可见（GONE而不是INVISIBLE）
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    /**
     * 给TextView添加超链接
     *
     * @param viewId 要添加超链接的TextView的id
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * 设置TextView文字样式
     *
     * @param typeface 文字样式
     * @param viewIds  要设置的TextView的id,可变参数设置多个
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * 设置ProgressBar进度
     *
     * @param viewId   要设置的Progress的id
     * @param progress 当前进度，默认最大进度为100
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置ProgressBar进度
     *
     * @param viewId   要设置的Progress的id
     * @param progress 当前进度
     * @param max      最大进度
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * 设置ProgressBar进度最大值
     *
     * @param viewId 要设置的Progress的id
     * @param max    最大进度
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setMax(int viewId, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    /**
     * 设置RatingBar的评分
     *
     * @param viewId 要设置的RatingBar的id
     * @param rating 星星评分
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置RatingBar的评分
     *
     * @param viewId 要设置的RatingBar的id
     * @param rating 星星评分
     * @param max    最大星星评分
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 给View设置标签tag
     *
     * @param viewId 要设置tag标签的View的id
     * @param tag    要存储的tag标签
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 给View设置标签tag,可以给一个View设置多个tag标签
     *
     * @param viewId 要设置tag标签的View的id
     * @param key    区分不同的tag，注意这个key是唯一的 通过values/strings.xml来定义
     * @param tag    要存储的tag标签
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     * 设置View的checked状态
     *
     * @param viewId  要设置的View的
     * @param checked true为checked状态，false为unchecked状态
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 给View设置点击监听
     *
     * @param viewId   要设置点击监听的View的id
     * @param listener 点击接口
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setOnClickListener(int viewId,
                                             View.OnClickListener listener) {
        View view = getView(viewId);
        if(view!=null)
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 给View设置触摸监听
     *
     * @param viewId   要设置触摸监听的View的id
     * @param listener 触摸接口
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setOnTouchListener(int viewId,
                                             View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 给View设置长按监听
     *
     * @param viewId   要设置长按监听的View的id
     * @param listener 长按接口
     * @return 返回当前view的ViewHolder
     */
    public BaseViewHolder setOnLongClickListener(int viewId,
                                                 View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

}

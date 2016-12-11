package com.ways2u.android.goapp.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Adapter基类

 */
public abstract  class HolderBaseAdapter<T extends List> extends BaseAdapter {

	protected Context context;

	/**
	 *
	 */
	private T mDatas;

	protected HolderBaseAdapter(Context context,T datas) {
		this.context = context;
		mDatas = datas;
	}


	/**
	 * 各个控件的缓存
	 */
	/*
	public class ViewHolder {
		public SparseArray<View> views = new SparseArray<View>();

		public <T extends View> T obtainView(View convertView, int resId) {
			View v = views.get(resId);
			if (null == v) {
				v = convertView.findViewById(resId);
				views.put(resId, v);
			}
			return (T) v;
		}

	}
*/
	/**
	 * 改方法需要子类实现，需要返回item布局的resource id
	 * 
	 * @return
	 */
	public abstract int getItemLayoutRes();


	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public T getItem(int position)	{
		return (T)mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	final public View getView(int position, View convertView, ViewGroup parent) {
		BaseViewHolder holder;
		if (null == convertView) {
			holder = new BaseViewHolder(context,parent,getItemLayoutRes(),position);
		} else {
			holder = (BaseViewHolder) convertView.getTag();
		}
		return bindView(position, convertView, parent, holder);
	}

	/**
	 * 使用该getView方法替换原来的getView方法，需要子类实现
	 * 
	 * @param position
	 * @param convertView
	 * @param parent
	 * @param holder
	 * @return
	 */
	public abstract View bindView(int position, View convertView,
			ViewGroup parent, BaseViewHolder holder);
	
	
}

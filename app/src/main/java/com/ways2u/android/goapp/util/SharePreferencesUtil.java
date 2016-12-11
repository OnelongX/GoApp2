package com.ways2u.android.goapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.ways2u.android.goapp.dagger.qualifier.DefaultQualifier;


/**
 * Created by Onelong on 16/3/3.
 */
/*
1. apply没有返回值而commit返回boolean表明修改是否提交成功
2. apply是将修改数据原子提交到内存, 而后异步真正提交到硬件磁盘, 而commit是同步的提交到硬件磁盘，
   因此，在多个并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。
   而apply只是原子的提交到内容，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。
3. apply方法不会提示任何失败的提示。
   由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，
   当然需要确保提交成功且有后续操作的话，还是需要用commit的。
 */
public class SharePreferencesUtil {

    private SharedPreferences sp;

    public SharePreferencesUtil(SharedPreferences preferences) {
        sp = preferences;
    }

    public String getString(String key) {
        String s = sp.getString(key, null);
        return s;
    }

    public String getString(String key, String defaultValue) {
        String s = sp.getString(key, defaultValue);
        return s;
    }

    public int getInt(String key) {
        int i = sp.getInt(key, 0);
        return i;
    }

    public int getInt(String key, int defaultValue) {
        int i = sp.getInt(key, defaultValue);
        return i;
    }

    public long getLong(String key) {
        long i = sp.getLong(key, 0);
        return i;
    }

    public long getLong(String key, long defaultValue) {
        long i = sp.getLong(key, defaultValue);
        return i;
    }

    public float getFloat(String key) {
        float i = sp.getFloat(key, 0);
        return i;
    }

    public float getFloat(String key, float defaultValue) {
        float i = sp.getFloat(key, defaultValue);
        return i;
    }

    public boolean getBoolean(String key) {
        boolean i = sp.getBoolean(key, false);
        return i;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        boolean i = sp.getBoolean(key, defaultValue);
        return i;
    }

    public void removeAndCommit(String key) {
        sp.edit().remove(key).commit();
    }

    public void setAndCommit(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public void setAndCommit(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public void setAndCommit(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public void setAndCommit(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public void setAndCommit(String key, float value) {
        sp.edit().putFloat(key, value).commit();
    }

    public void removeAndApply(String key) {
        sp.edit().remove(key).apply();
    }

    public void setAndApply(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public void setAndApply(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public void setAndApply(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public void setAndApply(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public void setAndApply(String key, float value) {
        sp.edit().putFloat(key, value).commit();
    }

}

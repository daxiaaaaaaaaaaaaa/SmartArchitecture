package com.architecturedemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.architecturedemo.CcsApplication;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

/**
 * sp工具类
 *
 * @author weishixiong
 * @Time 2018-04-20
 */
public class SPUtil {

    /**
     * 写数据
     *
     * @param spName sp参数名
     * @param key    键
     * @param value  值
     * @return
     */
    public static boolean putData(String spName, String key, Object value) {
        if (value==null) {
            return false;
        }
        Context context = CcsApplication.getInstance();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);//创建仅允许本应用使用的SharedPreferences
        Editor editor = preferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (float) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        } else if (value instanceof Set) {
            editor.putStringSet(key, (Set<String>) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (long) value);
        } else { //写对象
            try {
                Serializable serialObi = (Serializable) value;
                String base64Str = Base64.encodeObject(serialObi);//通过Spring 4 android将这个value对象转为字符串
                editor.putString(key, base64Str);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return false;
            } catch (ClassCastException caste) {
                return false;
            }
        }
        return editor.commit();
    }

    /**
     * @param spName     sp参数名
     * @param key        键
     * @param type       数据类型
     * @param defaultVal 默认值
     * @param <T>        泛型
     * @return
     */
    public static <T> T getData(String spName, String key, Class<T> type, T defaultVal) {
        Context context = CcsApplication.getInstance();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);//创建仅允许本应用使用的SharedPreferences
        if (String.class.equals(type)) {
            return (T) preferences.getString(key, defaultVal != null ? ((String) defaultVal) : null);
        } else if (Integer.class.equals(type)) {
            return (T) new Integer(preferences.getInt(key, defaultVal != null ? ((Integer) defaultVal) : null));
        } else if (Float.class.equals(type)) {
            return (T) new Float(preferences.getFloat(key, defaultVal != null ? ((Float) defaultVal) : null));
        } else if (Boolean.class.equals(type)) {
            return (T) new Boolean(preferences.getBoolean(key, defaultVal != null ? ((Boolean) defaultVal) : null));
        } else if (Set.class.equals(type)) {
            return (T) preferences.getStringSet(key, defaultVal != null ? ((Set) defaultVal) : null);
        } else if (Long.class.equals(type)) {
            return (T) new Long(preferences.getLong(key, defaultVal != null ? ((Long) defaultVal) : null));
        } else { //读对象
            try {
                String objStr = preferences.getString(key, "");
                if (TextUtils.isEmpty(objStr)) {
                    return null;
                }
                return (T) Base64.decodeToObject(objStr);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException cfe) {
                cfe.printStackTrace();
                return null;
            }
        }
    }

    /**
     * 清除某个sp数据
     *
     * @param spName sp参数名
     */
    public static void clearData(String spName) {
        Context context = CcsApplication.getInstance();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);//创建仅允许本应用使用的SharedPreferences
        preferences.edit().clear().commit();
    }


}

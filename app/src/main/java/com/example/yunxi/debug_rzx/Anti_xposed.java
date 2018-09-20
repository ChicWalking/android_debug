package com.example.yunxi.debug_rzx;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Anti_xposed {
    public static void xposed_text(){
        Class v1= MainActivity.class;
        synchronized (v1){
            try
            {
                Object localObject = ClassLoader.getSystemClassLoader()
                        .loadClass("de.robv.android.xposed.XposedHelpers").newInstance();
                // 如果加载类失败 则表示当前环境没有xposed
                if (localObject != null)
                {
                    a(localObject, "fieldCache");
                    a(localObject, "methodCache");
                    a(localObject, "constructorCache");
                }
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void a(Object arg5, String arg6) {
        try {
            // 从XposedHelpers中读取相关的hook信息
            Field v0_1 = arg5.getClass().getDeclaredField(arg6);
            v0_1.setAccessible(true);
            HashMap v0_2 = (HashMap) v0_1.get(arg5);
            if(v0_2 == null) {
                return;
            }
            if(v0_2.isEmpty()) {
                return;
            }
            Iterator v1 = v0_2.entrySet().iterator();
            // 排除无关紧要的类
            while(v1.hasNext()) {
                Map.Entry entry = (Map.Entry) v1.next();
                Object v0_3 = entry.getValue();
//                Object v0_3 = v1.next();
                if(v0_3 == null) {
                    continue;
                }
                if(((String)v0_3).length() <= 0) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("android.support")) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("javax.")) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("android.webkit")) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("java.util")) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("android.widget")) {
                    continue;
                }
                if(((String)v0_3).toLowerCase().startsWith("sun.")) {
                    continue;
                }
//                this.a.add(v0_3);
            }
        }
        catch(Throwable v0) {
            v0.printStackTrace();
        }
    }
}

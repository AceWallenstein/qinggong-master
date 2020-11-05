package com.national.qinggong.bean;

/**
 * 创建时间：2020/11/5
 * 编写人：czy_yangxudong
 * 功能描述：
 */

import java.lang.reflect.Field;
        import java.io.Serializable;
        import java.util.List;
public class AboutOursBean implements Serializable  {

    public String msg  ;
    public int code  ;
    public  Data data;
    public class Data implements Serializable  {
        public List<CategoryList> categoryList;
        public class CategoryList implements Serializable  {

            public String update_time  ;
            public String category_id  ;
            public String create_time  ;
            public String wxapp_id  ;
            public String name  ;
            public String sort  ;
            public String image_id  ;
            public  Image image;
            public class Image implements Serializable  {

                public String file_url  ;
                public String file_path  ;
                public String extension  ;
                public String group_id  ;
                public String file_name  ;
                public String file_type  ;
                public String is_recycle  ;
                public String file_id  ;
                public String storage  ;
                public String is_user  ;
                public String file_size  ;
                public String is_delete  ;
                @Override
                public String toString() {
                    String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
                }
            }
            @Override
            public String toString() {
                String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
            }
        }
        public List<Banner> banner;
        public class Banner implements Serializable  {

            public String update_time  ;
            public String create_time  ;
            public String banner_id  ;
            public String wxapp_id  ;
            public String name  ;
            public String sort  ;
            public String image_id  ;
            public String type  ;
            public String url  ;
            public String is_delete  ;
            public  Image image;
            public class Image implements Serializable  {

                public String file_url  ;
                public String file_path  ;
                public String extension  ;
                public String group_id  ;
                public String file_name  ;
                public String file_type  ;
                public String is_recycle  ;
                public String file_id  ;
                public String storage  ;
                public String is_user  ;
                public String file_size  ;
                public String is_delete  ;
                @Override
                public String toString() {
                    String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
                }
            }
            @Override
            public String toString() {
                String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
            }
        }

        @Override
        public String toString() {
            String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
        }
    }
    @Override
    public String toString() {
        String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
    }
}
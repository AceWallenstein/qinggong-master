package com.national.qinggong.bean;

/**
 * 创建时间：2020/11/16
 * 编写人：czy_yangxudong
 * 功能描述：
 */
import java.lang.reflect.Field;
        import java.io.Serializable;
        import java.util.List;
public class BrowseRecordsBean implements Serializable  {

    public String msg  ;
    public int code  ;
    public  Data data;
    public class Data implements Serializable  {

        public  Lists list;
        public class Lists implements Serializable  {

            public int per_page  ;
            public int total  ;
            public int last_page  ;
            public int current_page  ;
            public List<Datas> data;
            public class Datas implements Serializable  {

                public String update_time  ;
                public String create_time  ;
                public int user_id  ;
                public int wxapp_id  ;
                public int goods_id  ;
                public int id  ;
                public int is_delete  ;
                public  Goods goods;
                public class Goods implements Serializable  {

                    public String goods_name  ;
                    public String images  ;
                    public String create_time  ;
                    public int is_new  ;
                    public int wxapp_id  ;
                    public int goods_id  ;
                    public String more_info  ;
                    public String content  ;
                    public int is_delete  ;
                    public String number  ;
                    public String update_time  ;
                    public int category_id  ;
                    public int user_id  ;
                    public int is_hot  ;
                    public int is_recommend  ;
                    public String logo  ;
                    public int status  ;
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
    @Override
    public String toString() {
        String s = "";Field[] arr = this.getClass().getFields();for (Field f : getClass().getFields()) {try {s += f.getName() + "=" + f.get(this) + "\n,";} catch (Exception e) {}}return getClass().getSimpleName() + "[" + (arr.length==0?s:s.substring(0, s.length() - 1)) + "]";
    }
}
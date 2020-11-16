package com.national.qinggong.bean;

/**
 * 创建时间：2020/11/16
 * 编写人：czy_yangxudong
 * 功能描述：
 */
import java.lang.reflect.Field;
        import java.io.Serializable;
        import java.util.List;
public class PersonalDataBean implements Serializable  {

    public String msg  ;
    public int code  ;
    public  Data data;
    public class Data implements Serializable  {

        public  User user;
        public class User implements Serializable  {

            public int fid  ;
            public String country  ;
            public String role  ;
            public String gender  ;
            public String open_id  ;
            public String city  ;
            public int mid  ;
            public String pay_money  ;
            public int points  ;
            public int smile  ;
            public String scene  ;
            public String password  ;
            public String province  ;
            public String balance  ;
            public int grade_id  ;
            public int identity  ;
            public String source_cardid  ;
            public String company  ;
            public String source_name  ;
            public String address  ;
            public String avatarUrl  ;
            public String source_text  ;
            public String nickName  ;
            public int address_id  ;
            public String group_tags  ;
            public int source_type  ;
            public String phonemodel  ;
            public String lasttime  ;
            public String expend_money  ;
            public String weixin  ;
            public String phone  ;
            public int is_new_user  ;
            public String registrationid  ;
            public String position  ;
            public String source_id  ;
            public String job  ;
            public String account  ;
            public String username  ;
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
package com.national.qinggong.factory;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseConverter<T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private  int  flag;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();

        Log.i("=k线=6666666",  result+"");



//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
      /*  try {
            //原始数据下手
            String result = value.string();
            try {
                org.json.JSONObject jsonObject = null;
                jsonObject = new org.json.JSONObject(result);
                if(jsonObject != null) {
                    String reason = jsonObject.getString("code");
                    if (!TextUtils.isEmpty(reason) && TextUtils.equals(reason, "10001")) {//统一处理
                        EventBus.getDefault().post(new RefreshUrl(Constant.MSG_TOKEN_EXPIRE));
                    }
                }
            }catch (Exception e){

            }*/
            return adapter.fromJson(result);
//            return adapter.read(jsonReader);

    }
}

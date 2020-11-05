package com.national.qinggong.fragement;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.national.qinggong.R;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.RightDuihuanIntegralBean;
import com.national.qinggong.contract.IntegralConfirmContract;
import com.national.qinggong.dialog.OnDialogClickListener;
import com.national.qinggong.dialog.dialog.custom.AddUserAddressDialog;
import com.national.qinggong.dialog.dialog.custom.AlertPassDialog;
import com.national.qinggong.presenter.IntegralConfirmPresenter;
import com.national.qinggong.presenter.IntegralShopingDetailPresenter;
import com.national.qinggong.ui.activity.PlatformForFragmentActivity;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.GlideUtils;
import com.national.qinggong.util.StringUtils;
import com.national.qinggong.util.ToastUtilMsg;

import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/*
 * item_integral_confirm_shop.xml
 * */
public class FragmentIntegralConfirm extends BaseFragment implements IntegralConfirmContract.View {
    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.add_new_address)
    LinearLayout addNewAddress;



    @BindView(R.id.input_message)
    EditText inputMessage;
    @BindView(R.id.jifen_num)
    TextView jifenNum;
    @BindView(R.id.submit_order)
    LinearLayout submitOrder;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;

    @BindView(R.id.shop_list_lin)
    LinearLayout shop_list_lin;



    @BindView(R.id.user_address_info)
    TextView user_address_info;

    @BindView(R.id.user_address_detail)
    TextView user_address_detail;

    int  flagUserAddress;
    RightDuihuanIntegralBean.DataBean.AddressBean getAddress;
    Unbinder unbinder;
    private AddUserAddressDialog userAddressDialog;
    private EditText input_address;
    private EditText add_phone;
    private EditText address_name;

    public static FragmentIntegralConfirm newInstance() {
        Bundle args = new Bundle();
        FragmentIntegralConfirm fragment = new FragmentIntegralConfirm();
        fragment.setArguments(args);
        return fragment;
    }
    public void submitRightNow(String goods_id, String goods_num, String goods_sku_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("goods_id", goods_id);
        map.put("goods_num", goods_num);
        map.put("goods_sku_id", "0");
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().submitRightInfo(map);

    }
/*提交兑换订单
 */
    public void submitOrder(String goods_id, String goods_num, String goods_sku_id,String remark) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("delivery", "10");
        map.put("pay_type", "10");
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("remark", remark);
        map.put("goods_id", goods_id);
        map.put("goods_num", goods_num);
        map.put("goods_sku_id", "0");
        getPresenter().submitOrderInfo(map);

    }



    @Override
    protected void initdata() {
        updateaddress_detail();
    }
    @Override
    protected IntegralConfirmPresenter getPresenter() {
        return new IntegralConfirmPresenter(_mActivity, FragmentIntegralConfirm.this);
    }



    private void add_Address(String name,String phone,String detail) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);
        map.put("detail", detail);
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().addAddressInfo(map);

    }


    private void edit_Address(String name,String phone,String detail,String address_id) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");

        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);
        map.put("detail", detail);
        map.put("region", "");
        map.put("address_id", address_id);
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        getPresenter().editAddressInfo(map);

    }








    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral_confirm;
    }


    @OnClick({R.id.rl_back, R.id.add_new_address, R.id.submit_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.add_new_address:
                if (flagUserAddress == 1) {//有地址
                    showAlertPassDialog(getAddress.getName(),getAddress.getPhone(),getAddress.getDetail(),getAddress.getAddress_id()+"");
                }else {
                    showAlertPassDialog("","","","");
                }
                break;
            case R.id.submit_order:
                if (flagUserAddress == 1) {
                    String remark = inputMessage.getText().toString().trim();
                    submitOrder("11709","1","",remark);
                }else {
                    ToastUtilMsg.showToast(_mActivity, "Please fill in the shipping address");
                }
                break;
        }
        /*
        * 添加地址对话框
        * */
    } public void showAlertPassDialog(String  username,String userphone,String addressdetail ,String address_id) {
        userAddressDialog = new AddUserAddressDialog(_mActivity);
//        alertPassDialog.setText(R.id.tip_message_tv,"");
        input_address = (EditText) userAddressDialog.findView(R.id.input_address);
        address_name = (EditText) userAddressDialog.findView(R.id.address_name);
        add_phone = (EditText) userAddressDialog.findView(R.id.add_phone);

        add_phone.setText(userphone+"");
        address_name.setText(username+"");
        input_address.setText(addressdetail+"");
        userAddressDialog.show();
        userAddressDialog.setOnDialogClickListener(new OnDialogClickListener() {
            @Override
            public void onDialogClick(Dialog dialog, int id) {
                switch (id) {
                    case R.id.submit_alert:
                        if (StringUtils.isEmpty(address_name.getText().toString())) {
                            Toast.makeText(_mActivity, "Please enter the User Name", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (StringUtils.isEmpty(add_phone.getText().toString())) {
                            Toast.makeText(_mActivity, "Please enter the Phone", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (StringUtils.isEmpty(input_address.getText().toString())) {
                            Toast.makeText(_mActivity, "Please enter the address", Toast.LENGTH_LONG).show();
                            return;
                        }
//                        getUserInfo(input_address.getText().toString());
                        if (flagUserAddress == 1) {
                            edit_Address(address_name.getText().toString(),add_phone.getText().toString(),input_address.getText().toString(),getAddress.getAddress_id()+"");
                        }else {
                            add_Address(address_name.getText().toString(),add_phone.getText().toString(),input_address.getText().toString());
                        }
                        userAddressDialog.dismiss();
//                        isSaveEdit=true;
//                        isEdit = true;
//                        isRecruEdit = true;
//                        _mActivity.onBackPressed();
                        break;

                    case R.id.submit_alert_dis:
                        userAddressDialog.dismiss();
                        break;

                }
            }
        });
    }

    @Override
    public void addAddressTask(BaseBean userInfo) {
      if (userInfo!=null){
          if (userInfo.getCode()==1){
              flagUserAddress=1;
              updateaddress_detail();
              ToastUtilMsg.showToast(_mActivity,userInfo.getMsg()+"");
          }
      }
    }

  public  void   updateaddress_detail(){
      String good_id = CacheHelper.getAlias(_mActivity, "good_id");
      String goods_num = CacheHelper.getAlias(_mActivity, "goods_num");
      submitRightNow(good_id,goods_num,"");
    }
    @Override
    public void editAddressTask(BaseBean userInfo) {
        if (userInfo!=null){
            if (userInfo.getCode()==1){
                flagUserAddress=1;
                updateaddress_detail();
                ToastUtilMsg.showToast(_mActivity,userInfo.getMsg()+"");
            }
        }
    }

    @Override
    public void RightDuihuan(Object userInfo) {
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        JSONObject jsonObject = null;
        RightDuihuanIntegralBean homeBean = new Gson().fromJson(jsoncontent, new TypeToken<RightDuihuanIntegralBean>() {
        }.getType());
        if (homeBean!=null){
            RightDuihuanIntegralBean.DataBean getData = homeBean.getData();
            if (getData.isHas_error()){
                ToastUtilMsg.showToast(_mActivity,getData.getError_msg()+"");
            }else {
                 getAddress = getData.getAddress();
                if (getAddress!=null){
                    flagUserAddress=1;
                    user_address_info.setText(""+getAddress.getName()+"    "+getAddress.getPhone());
                    user_address_detail.setText(getAddress.getDetail()+"");
                }
                List<RightDuihuanIntegralBean.DataBean.GoodsListBean> getGoods_list = getData.getGoods_list();
                shop_list_lin.removeAllViews();
                if (getGoods_list != null && getGoods_list.size() > 0) {
                    for (int i = 0; i < getGoods_list.size(); i++) {
                        View firstview = LayoutInflater.from(_mActivity).inflate(R.layout.item_integral_confirm_shop, null);
                        TextView title = (TextView) firstview.findViewById(R.id.name_product);
                        TextView total_points = (TextView) firstview.findViewById(R.id.total_points);
                        TextView jifen_detail = (TextView) firstview.findViewById(R.id.jifen_detail);
                        TextView number = (TextView) firstview.findViewById(R.id.number);
                        ImageView ivGoods = (ImageView) firstview.findViewById(R.id.product_image);
                        title.setText(getGoods_list.get(i).getGoods_name());
                        jifen_detail.setText(""+getGoods_list.get(i).getNeed_points_num()+" Credits");
                        total_points.setText(""+getGoods_list.get(i).getTotal_need_points_num());
                        number.setText("X" + getGoods_list.get(i).getTotal_num() + "");
                        GlideUtils.loadImageByUrl(getGoods_list.get(i).getGoods_image(), ivGoods);
                        shop_list_lin.addView(firstview);
                    }
                }
                jifenNum.setText(getData.getExchange_need_total_points()+"");
            }
        }
    }

    /*订单信息*/

    /*
    *  {"code":0,"msg":"用户积分不足，无法兑换商品","data":[]}
    * */
    @Override
    public void submitOrderTask(Object userInfo) {
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsoncontent);
            int reason = jsonObject.getInt("code");
            String msg = jsonObject.getString("msg");
            if (reason==0){
                ToastUtilMsg.showToast(_mActivity,msg+"");
            }else {
                ToastUtilMsg.showToast(_mActivity,msg+"");
            }



            BaseBean homeBean = new Gson().fromJson(jsoncontent, new TypeToken<BaseBean>() {
            }.getType());
            Log.i("order",userInfo.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void showToast(String content) {

    }
}

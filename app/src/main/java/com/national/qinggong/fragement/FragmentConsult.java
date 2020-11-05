package com.national.qinggong.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.national.qinggong.R;
import com.national.qinggong.adapter.ConsultAdapter;
import com.national.qinggong.adapter.JoneBaseAdapter;
import com.national.qinggong.base.BaseFragment;
import com.national.qinggong.bean.ChatConsultBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.RefreshUrl;
import com.national.qinggong.bean.UploadImageBean;
import com.national.qinggong.contract.ConsultListContract;
import com.national.qinggong.customview.EmptyLayout;
import com.national.qinggong.presenter.ConsultListPresenter;
import com.national.qinggong.request.API;
import com.national.qinggong.request.RequestManager;
import com.national.qinggong.request.RetrofitClient;
import com.national.qinggong.util.CacheHelper;
import com.national.qinggong.util.LoadingUtil;
import com.national.qinggong.util.MyGlideImageLoader;
import com.national.qinggong.util.StringUtils;
import com.yxd.imagepickers.ImagePicker;
import com.yxd.imagepickers.bean.ImageItem;
import com.yxd.imagepickers.ui.ImageGridActivity;
import com.yxd.imagepickers.ui.ImagePreviewActivity;
import com.yxd.imagepickers.view.CropImageView;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*
 * 询价列表
 * ConsultListPresenter
 * com.national.qinggong.bean.ChatConsultBean
 * */
public class FragmentConsult extends BaseFragment implements ConsultListContract.View {

    private ProgressLayout mProgressLayout;

    @BindView(R.id.head_content)
    LinearLayout headContent;
    @BindView(R.id.send_image)
    ImageView sendImage;
    @BindView(R.id.input_content)
    EditText inputContent;
    @BindView(R.id.send_message)
    ImageView sendMessage;
    @BindView(R.id.twinkling_refreshlayout)
    TwinklingRefreshLayout twinkling_refreshlayout;
    private boolean mIsRefresh = true;
    private int mIndex = 1;

    private List<ChatConsultBean.DataBeanX.ListBean.DataBean> messageList = new ArrayList<>();

    @BindView(R.id.rl_back)
    LinearLayout rlBack;
    @BindView(R.id.consult_recyclerView)
    RecyclerView consultRecyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private LinearLayoutManager linearmanager;
    private ConsultAdapter mBestjobAdapter;
    private String user_id;
    private MyGlideImageLoader myGlideImageLoader;

    public static FragmentConsult newInstance(String into_type) {
        Bundle args = new Bundle();
        FragmentConsult fragment = new FragmentConsult();
        args.putString("user_id", into_type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user_id = getArguments().getString("user_id");
    }

    @Override
    protected ConsultListPresenter getPresenter() {
        return new ConsultListPresenter(_mActivity, FragmentConsult.this);
    }

    public void initView() {
        linearmanager = new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false);
        consultRecyclerView.setLayoutManager(linearmanager);
        mBestjobAdapter = new ConsultAdapter();
        mBestjobAdapter.setUser_id(user_id);
        consultRecyclerView.setAdapter(mBestjobAdapter);
    }

    private void initRefresh() {
        mProgressLayout = new ProgressLayout(_mActivity);
        twinkling_refreshlayout.setHeaderView(mProgressLayout);
        twinkling_refreshlayout.setFloatRefresh(true);
        twinkling_refreshlayout.setEnableOverScroll(false);
        twinkling_refreshlayout.setHeaderHeight(100);
        twinkling_refreshlayout.setMaxHeadHeight(120);
        twinkling_refreshlayout.setBottomHeight(70);
        twinkling_refreshlayout.setMaxBottomHeight(90);
        twinkling_refreshlayout.setTargetView(null);
        twinkling_refreshlayout.setEnableLoadmore(true);
        twinkling_refreshlayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mIsRefresh = true;
                mIndex = 1;
                getconsultListInfo(user_id, mIndex + "");
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mIsRefresh = false;
                mIndex += 1;
                getconsultListInfo(user_id, mIndex + "");
            }
        });
        twinkling_refreshlayout.startRefresh();

    }

    @Override
    protected void initdata() {
        myGlideImageLoader = new MyGlideImageLoader();
        initView();

        initRefresh();

        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.getInstance().setSelectLimit(1);
                ImagePicker.getInstance().setCrop(false);
                ImagePicker.getInstance().setMultiMode(false);
                ImagePicker.getInstance().setStyle(CropImageView.Style.CIRCLE);
                Intent intent = new Intent(_mActivity, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, false); // 是否是直接打开相机
                startActivityForResult(intent, 12346);
            }
        });
    }

    private void getconsultListInfo(String to_user_id, String page) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("to_user_id", to_user_id);
        map.put("page", page);
        getPresenter().getConsultList(map);
    }

    private void submit_send_content(String to_user_id, String content, String type) {
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, String> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        map.put("to_user_id", to_user_id);
        map.put("content", content);
        map.put("type", type);//text(text:文字，image:图片)
        getPresenter().getSendConsult(map);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_consult_recycle;
    }

    @Subscribe
    public void refreshData(RefreshUrl refreshUrl) {
        if (null == refreshUrl) {
            return;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == 12346) {
                List<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    try {
                        File file = new File(images.get(0).path);
                        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), file);
                        MultipartBody.Part file1 = MultipartBody.Part.createFormData("iFile", images.get(0).path, requestFile);
                        uploadImage(file1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
        }
    }

    private void uploadImage(MultipartBody.Part iFile) {
        LoadingUtil.show(_mActivity);
        String getToken = CacheHelper.getAlias(_mActivity, "getToken");
        Map<String, Object> map = new HashMap<>();
        map.put("wxapp_id", "10001");
        map.put("token", getToken);
        // map.put("name", getToken+new Date().getTime());
        RetrofitClient.getApiService(API.APP_QING_GONG)
                .uploadImage(map, iFile)
                .compose(RequestManager.<UploadImageBean>applyIoSchedulers())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                    }
                })
                .subscribe(new Consumer<UploadImageBean>() {
                               @Override
                               public void accept(UploadImageBean userInfo) throws Exception {
                                   if(userInfo.getCode()==1){
                                       String avatarUrl = userInfo.getData().getFile_path();
                                       submit_send_content(user_id, avatarUrl, "image");
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );
    }

    @Override
    public void refreshConsultPage(Object userInfo) {
        Gson gson = new Gson();
        String jsoncontent = gson.toJson(userInfo);
        ChatConsultBean consultBean = new Gson().fromJson(jsoncontent, new TypeToken<ChatConsultBean>() {
        }.getType());
        if (consultBean != null) {
            int getCode = consultBean.getCode();
            if (getCode == 1) {
                ChatConsultBean.DataBeanX getData = consultBean.getData();
                if (getData != null) {
                    tv_title.setText(getData.getTo_user().getNickName());
                    ChatConsultBean.DataBeanX.ListBean messagegetList = getData.getList();
                    if(mIndex==1){
                        mBestjobAdapter.getmMessageList().clear();
                    }
                    if (messagegetList.getData() != null & messagegetList.getData().size() > 0) {
                        mBestjobAdapter.setData(messagegetList.getData());
                    } else {
                        mIndex = mIndex - 1;
                    }
                } else {
                    mIndex = mIndex - 1;
                }

            }
        }
        twinkling_refreshlayout.setEnableLoadmore(true);
    }

    @Override
    public void stockFinish() {

    }

    @Override
    public void Disposable() {
        if (twinkling_refreshlayout == null) {
            return;
        }
        if (mIsRefresh) {
            twinkling_refreshlayout.finishRefreshing();
        } else {
            twinkling_refreshlayout.finishLoadmore();
        }
    }


    @Override
    public void sendConsult(DeleteCarBean userInfo) {
        if (userInfo != null) {
            int getCode = userInfo.getCode();
            if (getCode == 1) {
                hideSoftInput();
                inputContent.setText("");
                LoadingUtil.hide();
                twinkling_refreshlayout.startRefresh();
            }
        }
    }

    @Override
    public void showToast(String content) {

    }

    @OnClick({R.id.rl_back, R.id.consult_recyclerView, R.id.send_image, R.id.input_content, R.id.send_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                _mActivity.onBackPressed();
                break;
            case R.id.consult_recyclerView:
                break;
            case R.id.send_image:
                break;
            case R.id.input_content:
                break;
            case R.id.send_message:
                String inputcontent = inputContent.getText().toString().trim();
                if (!StringUtils.isEmpty(inputcontent)) {
                    LoadingUtil.show(_mActivity);
                    submit_send_content(user_id, inputcontent, "text");
                }
                break;
        }
    }


}

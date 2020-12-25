package com.national.qinggong.request;


import com.national.qinggong.bean.AboutOursBean;
import com.national.qinggong.bean.AboutOursNewsBean;
import com.national.qinggong.bean.ActivityArticleBean;
import com.national.qinggong.bean.ActivityDetailBean;
import com.national.qinggong.bean.ArticleDetailBean;
import com.national.qinggong.bean.BaseBean;
import com.national.qinggong.bean.BaseStrBean;
import com.national.qinggong.bean.BrowseRecordsBean;
import com.national.qinggong.bean.CarBean;
import com.national.qinggong.bean.ChangePasswordBean;
import com.national.qinggong.bean.ClassRoomDetailBean;
import com.national.qinggong.bean.ClassTypeBean;
import com.national.qinggong.bean.CollectBean;
import com.national.qinggong.bean.CountryBean;
import com.national.qinggong.bean.DeleteCarBean;
import com.national.qinggong.bean.DoTaskBean;
import com.national.qinggong.bean.FaceAdviseBean;
import com.national.qinggong.bean.HelpCenterBean;
import com.national.qinggong.bean.HomeBannerBean;
import com.national.qinggong.bean.HomeMessagebean;
import com.national.qinggong.bean.HomePageBean;
import com.national.qinggong.bean.IndexCountryBean;
import com.national.qinggong.bean.IndexGetBannerBean;
import com.national.qinggong.bean.IntegralDetailBean;
import com.national.qinggong.bean.IntegralMingXiBean;
import com.national.qinggong.bean.IntegralShopBean;
import com.national.qinggong.bean.LiveAnchorDetailBean;
import com.national.qinggong.bean.LiveListBackBean;
import com.national.qinggong.bean.LiveListBackBean2;
import com.national.qinggong.bean.LivePeopleBean;
import com.national.qinggong.bean.LiveRoomDetailBean;
import com.national.qinggong.bean.LiveRoomGetTalkBean;
import com.national.qinggong.bean.LiveRoomGoodsListBean;
import com.national.qinggong.bean.LiveRoomListBean;
import com.national.qinggong.bean.LiveRoomMyTopicBean;
import com.national.qinggong.bean.LiveRoomTopicListBean;
import com.national.qinggong.bean.LiveVideoListBean;
import com.national.qinggong.bean.MultiMarketBean;
import com.national.qinggong.bean.MyFansBean;
import com.national.qinggong.bean.MyMessageBean;
import com.national.qinggong.bean.NewsDetailBean;
import com.national.qinggong.bean.PersonCenterBean;
import com.national.qinggong.bean.PersonalDataBean;
import com.national.qinggong.bean.ReadUserInfoBean;
import com.national.qinggong.bean.SaleCollectBean;
import com.national.qinggong.bean.SaleManBean;
import com.national.qinggong.bean.SearchBean;
import com.national.qinggong.bean.SearchCategoryBean;
import com.national.qinggong.bean.SearchShopListBean;
import com.national.qinggong.bean.SendIntegralPointsBean;
import com.national.qinggong.bean.ServiceQrBean;
import com.national.qinggong.bean.ShopDetailBean;
import com.national.qinggong.bean.StockBlockBean;
import com.national.qinggong.bean.SysArticleBean;
import com.national.qinggong.bean.TaskBean;
import com.national.qinggong.bean.UploadImageBean;
import com.national.qinggong.bean.VisitsBean;
import com.national.qinggong.bean.ZhiDingPriceBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

/**
 * 接口请求数据
 */

public interface ApiService {

    /**
     * 获取消息文章
     */
    @GET("api/article/list")
    Observable<ClassTypeBean> getUserInfo(@QueryMap Map<String, String> queryMap);


    /**
     * 获取活动文章
     */
    @GET("api/activity/list")
    Observable<ActivityArticleBean> getActivityArticleInfo(@QueryMap Map<String, String> queryMap);


    /**
     * 课堂列表
     */
    @GET("api/course/list")
    Observable<ClassTypeBean> getClassRoomInfo(@QueryMap Map<String, String> queryMap);


    /**
     * 热门课堂列表
     * http://guofu.vshop365.cn/api/course/hot
     */
    @GET("api/course/hot")
    Observable<ClassTypeBean> gethotClassRoomInfo();
//


    /**
     * banner
     */
    @GET("api/pageIndex")
    Observable<HomeBannerBean> getBannerInfo();

    @GET("Login")
    Observable<Object> getUserinfo(@QueryMap Map<String, String> queryMap);


    @GET("Login")
    Observable<Object> getUserLogin(@QueryMap Map<String, String> queryMap);


    @GET("GetAllWaiPanMarketModel")
    Observable<Object> getContractinfo();


    /* 4、得到市场所有股票、期货合约的市场、代码、名称集合
    获取板块tab
    * */
    @GET("GetAllMarketModel")
    Observable<List<StockBlockBean.StockListbean>> getBlockInfo();


    /*
     * 12
     * http://120.24.219.141:12002/StockService/GetStockDatas?StkLabels=SH600000,SZ000001
     * */
    @GET("GetStockDatas")
    Observable<List<MultiMarketBean>> getMultiMarketInfo(@QueryMap Map<String, String> queryMap);


    /*
     *
     *
     * http://120.24.219.141:12002/StockService/GetAllMarketModel
     * */


//    http://120.24.219.141:12002/StockService/


    @GET("UserInfo")
    Observable<Object> getCurrentUserinfo(@QueryMap Map<String, String> queryMap);


    /*
     *
     * */
    @GET("GetStockData")
    Observable<ZhiDingPriceBean> getSingleMarketInfo(@QueryMap Map<String, String> queryMap);


    @GET("api/article/view")
    Observable<ArticleDetailBean> getArticleDetailInfo(@QueryMap Map<String, String> queryMap);

    @GET("api/course/view")
    Observable<ClassRoomDetailBean> getClassDetailInfo(@QueryMap Map<String, String> queryMap);


    @GET("api/index/msglist")
    Observable<HomeMessagebean> getMessageInfo(@QueryMap Map<String, String> queryMap);


    @GET("api/index/readMsg")
    Observable<ReadUserInfoBean> getReadMessageInfo(@QueryMap Map<String, String> queryMap);


    @GET("api/activity/view")
    Observable<ActivityDetailBean> getActivityDetailInfo(@QueryMap Map<String, String> queryMap);


    /*
     * http://120.24.219.141:8802/BackService/UpdatePassword?username=test&password=123456
     * */
    @GET("BackService/UpdatePassword")
    Observable<Object> getUserAlertPass(@QueryMap Map<String, String> queryMap);


    @GET("api/index/config")
    Observable<ServiceQrBean> getPhoneQrInfo();

    @GET("api/index/tasks")
    Observable<TaskBean> getNewHandTask(@QueryMap Map<String, String> queryMap);

    //    完成任务的接口 index/taskdone  GET请求。task：任务ID    username：用户名
    @GET("api/index/taskdone")
    Observable<DoTaskBean> getDoTask(@QueryMap Map<String, String> queryMap);

    @GET("api/index/search")
    Observable<ClassTypeBean> getSearch(@QueryMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("api/index/feed")
    Observable<FaceAdviseBean> getFaceAdviseInfo(@Field("phone") String phone, @Field("content") String content
            , @Field("username") String username);


    @POST("api/index/quora?page=1")
    Observable<HelpCenterBean> getHelpnfo(@QueryMap Map<String, String> queryMap);

    @GET("IsConnected")
    Observable<Object> IsConnected(@QueryMap Map<String, String> queryMap);


    @FormUrlEncoded
    @POST("index.php?s=/api/user/sendCode")
    Observable<BaseStrBean> sendSms(@FieldMap Map<String, String> queryMap);


    /*http://qingong.meiliancheng.cn/index.php?s=/api/user/register*/
    @FormUrlEncoded
    @POST("index.php?s=/api/user/register")
    Observable<Object> registerUSER(@FieldMap Map<String, String> queryMap);
    //http://qgshop.meiliancheng.cn/index.php?s=/api/user/forgetPassword
    @FormUrlEncoded
    @POST("index.php?s=/api/user/forgetPassword")
    Observable<BaseBean> findPass(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/user/login")
    Observable<Object> getuserLoginTask(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/user/smsLogin")
    Observable<Object> SmsLogin(@FieldMap Map<String, String> queryMap);

    /*首页*/
    @FormUrlEncoded
    @POST("index.php?s=/api/index/index")
    Observable<HomePageBean> homePage(@FieldMap Map<String, String> queryMap);

    //修改资料
    @FormUrlEncoded
    @POST("index.php?s=/api/user/edit")
    Observable<Object> userEdit(@FieldMap Map<String, String> queryMap);

    //国家列表
    @FormUrlEncoded
    @POST("index.php?s=/api/index/country")
    Observable<IndexCountryBean> indexCountry(@FieldMap Map<String, String> queryMap);

    //
    @FormUrlEncoded
    @POST("index.php?s=/api/user/changePassword")
    Observable<ChangePasswordBean> changePassword(@FieldMap Map<String, String> queryMap);

    //文件上传（图片，视频）
    @Multipart
    @POST("index.php?s=/api/upload/image")
    Observable<UploadImageBean> uploadImage(@QueryMap Map<String, Object> queryMap, @Part MultipartBody.Part file);


    /*商品详情*/
    @FormUrlEncoded
    @POST("index.php?s=/api/index/detail")
    Observable<ShopDetailBean> homeDetail(@FieldMap Map<String, String> queryMap);

    /*注册*/
    @FormUrlEncoded
    @POST("index.php?s=/api/user/register")
    Observable<Object> userRegist(@FieldMap Map<String, String> queryMap);

    /*积分商城
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/goods/lists")
    Observable<Object> integralShoping(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/index/getBanner")
    Observable<IndexGetBannerBean> indexGetBanner(@FieldMap Map<String, String> queryMap);


    //积分订单
    @FormUrlEncoded
    @POST("index.php?s=/api/user.order/lists")
    Observable<IntegralShopBean> userOrderLists(@FieldMap Map<String, String> queryMap);

    /*积分详情
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/goods/detail")
    Observable<IntegralDetailBean> integralShopingDetail(@FieldMap Map<String, String> queryMap);

    /*立即兑换*/
    @GET("index.php?s=/api/points.order/checkout")
    Observable<Object> right_integral(@QueryMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/address/add")
    Observable<BaseBean> addAddress(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/address/edit")
    Observable<BaseBean> editAddress(@FieldMap Map<String, String> queryMap);


    /*新闻列表
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/article/lists")
    Observable<AboutOursNewsBean> newsList(@FieldMap Map<String, String> queryMap);

    /*文章首页
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/article/index")
    Observable<AboutOursBean> getAboutOurs(@FieldMap Map<String, String> queryMap);


    /*新闻详情
http://qingong.meiliancheng.cn/index.php?s=/api/article/detail
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/article/detail")
    Observable<NewsDetailBean> newsDetailList(@FieldMap Map<String, String> queryMap);

    /*
     *
     * 系统文章
     * */
    @FormUrlEncoded
    @POST("index.php?s=/api/article/systemArticle")
    Observable<SysArticleBean> sysArticle(@FieldMap Map<String, String> queryMap);

    /*
     * 个人中心
     * */
    @FormUrlEncoded
    @POST("index.php?s=/api/user/detail")
    Observable<PersonCenterBean> personCenter(@FieldMap Map<String, String> queryMap);


    /*
     *
     * */
    @FormUrlEncoded
    @POST("index.php?s=/api/salesman/searchList")
    Observable<SaleManBean> salemanList(@FieldMap Map<String, String> queryMap);


    @FormUrlEncoded
    @POST("index.php?s=/api/index/searchIndex")
    Observable<SearchBean> searchPage(@FieldMap Map<String, String> queryMap);

    //    http://qingong.meiliancheng.cn/index.php?s=/api/index/goodsList
    @FormUrlEncoded
    @POST("index.php?s=/api/index/goodsList")
    Observable<SearchShopListBean> searchShopList(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/category/index")
    Observable<SearchCategoryBean> searchCategoryShopList(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/points.log/index")
    Observable<IntegralMingXiBean> IntegralMingXi(@FieldMap Map<String, String> queryMap);


    /*送积分*/
    @FormUrlEncoded
    @POST("index.php?s=/api/index/sendPoint")
    Observable<SendIntegralPointsBean> sendIntegralPoints(@FieldMap Map<String, String> queryMap);


    /*提交兑换订单
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/points.order/checkout")
    Observable<Object> submitOrder(@FieldMap Map<String, String> queryMap);


    /*我的消息
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/user.chat/messageList")
    Observable<MyMessageBean> myMessage(@FieldMap Map<String, String> queryMap);

    /*我的收藏
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/salesman/list")
    Observable<SaleCollectBean> myCollect(@FieldMap Map<String, String> queryMap);


    /*取消收藏/收藏业务员
     */
    @FormUrlEncoded
    @POST("index.php?s=/api/salesman/like")
    Observable<CollectBean> add_cancle_Collect(@FieldMap Map<String, String> queryMap);
//    http://qingong.meiliancheng.cn/index.php?s=/api/salesman/like&wxapp_id=10001&token=6900314512ee92d52ff0e3e59c550a13&sales_user_id=14662

    /*购物车列表*/
    @FormUrlEncoded
    @POST("index.php?s=/api/goods/cartList")
    Observable<CarBean> CarList(@FieldMap Map<String, String> queryMap);

    /*删除购物车*/
    @FormUrlEncoded
    @POST("index.php?s=/api/goods/delCart")
    Observable<DeleteCarBean> deleteCar(@FieldMap Map<String, String> queryMap);

    /*
     *
     * 添加购物车*/
    @FormUrlEncoded
    @POST("index.php?s=/api/goods/addCart")
    Observable<DeleteCarBean> addCar(@FieldMap Map<String, String> queryMap);

    /*
     *
     * 询价列表*/
    @FormUrlEncoded
    @POST("index.php?s=/api/user.chat/list")
    Observable<Object> consultList(@FieldMap Map<String, String> queryMap);

    /*
     *
     * 发送询价*/
    @FormUrlEncoded
    @POST("index.php?s=/api/user.chat/sendMsg")
    Observable<DeleteCarBean> sendConsult(@FieldMap Map<String, String> queryMap);

    /*
     *
     * 国家列表
     *
     * http://qingong.meiliancheng.cn/index.php?s=/api/index/country
     * */
    @FormUrlEncoded
    @POST("index.php?s=/api/index/country")
    Observable<CountryBean> countryList(@FieldMap Map<String, String> queryMap);

    //直播列表
    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/index")
    Observable<LiveRoomListBean> liveRoomList(@FieldMap Map<String, String> queryMap);

    //直播列表
    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/getVideoAll")
    Observable<LiveListBackBean> live_roomBack(@FieldMap Map<String, String> queryMap);

    //直播列表
    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/myTopic")
    Observable<LiveRoomMyTopicBean> live_roomMyTopic(@FieldMap Map<String, String> queryMap);

    //主播的观看记录
    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/viewLog")
    Observable<VisitsBean> getVisits(@FieldMap Map<String, String> queryMap);

    //根据用户id获取用户信息
    @FormUrlEncoded
    @POST("index.php?s=/api/user/userInfo")
    Observable<PersonalDataBean> getPersonalData(@FieldMap Map<String, String> queryMap);

    //粉丝列表
    //index.php?s=/api/live.anchor/fansList
    @FormUrlEncoded
    @POST("index.php?s=/api/live.anchor/fansList")
    Observable<MyFansBean> getFans(@FieldMap Map<String, String> queryMap);

    //我的足迹
    @FormUrlEncoded
    @POST("index.php?s=/api/user/viewLog")
    Observable<BrowseRecordsBean> getBrowseRecords(@FieldMap Map<String, String> queryMap);


    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/goodsList")
    Observable<LiveRoomGoodsListBean> live_roomGoodsList(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.video/list")
    Observable<LiveVideoListBean> liveVideoList(@FieldMap Map<String, String> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.video/add")
    Observable<Object> liveVideoAdd(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.video/delete")
    Observable<BaseBean> liveVideoDelete(@FieldMap Map<String, Object> queryMap);
    @FormUrlEncoded
    @POST("index.php?s=/api/live.video/choseVideo")
    Observable<BaseBean> choseVideo(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.video/cancelVideo")
    Observable<BaseBean> cancelVideo(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/add")
    Observable<Object> Live_roomAdd(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/myTopicList")
    Observable<LiveRoomTopicListBean> live_roomTopicList(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/delTopic")
    Observable<BaseBean> live_roomdelTopic(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/sendTalk")
    Observable<Object> Live_roomSendTalk(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/getTalk")
    Observable<LiveRoomGetTalkBean> Live_roomGetTalk(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/setStatus")
    Observable<Object> Live_roomSetStatus(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/detail")
    Observable<LiveRoomDetailBean> Live_roomDetail(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.anchor/detail")
    Observable<LiveAnchorDetailBean> Live_AnchorDetail(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/getVideobyAnchor")
    Observable<LiveListBackBean2> Live_getVideobyAnchor(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.anchor/focus")
    Observable<Object> livefocus(@FieldMap Map<String, Object> queryMap);

    @FormUrlEncoded
    @POST("index.php?s=/api/live.anchor/cancelFocus")
    Observable<Object> liveCancelFocus(@FieldMap Map<String, Object> queryMap);

    //获取直播间人数
    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/people")
    Observable<LivePeopleBean> getLivePeople(@FieldMap Map<String, Object> queryMap);

    //用户进入、退出直播间
    @FormUrlEncoded
    @POST("index.php?s=/api/live.live_room/userEnter")
    Observable<Object> userEnterOrOut(@FieldMap Map<String, Object> queryMap);

    //设置直播间状态
    @FormUrlEncoded
    @POST("index.php?s=/api/live.Live_room/setStatus")
    Observable<Object> setStatus(@FieldMap Map<String, String> queryMap);


}

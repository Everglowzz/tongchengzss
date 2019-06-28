package hzyj.come.zhangshangpingtai.app.https.config;


public interface NetWorkConstant {

//    String HOST_VALUE = "http://192.168.2.193:8080/wygl/";
    String HOST_VALUE = "http://211.159.220.67:8888/";
    
      /*新 */

    String housenumber = "housenumber";
    String mobile = "mobile";
    int REQUEST_SUCCESS = 200;
    String ownid = "ownInfoId";
    String ownName = "ownName";


    //登录
    String app_login = HOST_VALUE + "appLogin/applogins";
    //查询缴费订单
    String selectchasyn = HOST_VALUE + "appLogin/selectchasyn";
    //查询缴费项目
    String project_query = HOST_VALUE + "appLogin/gainProjectName";
    //物业缴费
    String payment = HOST_VALUE + "appLogin/getChargeList";
    //历史缴费详情
    String selectdetails = HOST_VALUE + "appLogin/selectdetails";
    //项目缴费详情
    String gainProject = HOST_VALUE + "appLogin/gainProject";
    //报修记录查询
    String repairsList = HOST_VALUE + "appLogin/repairsList";
    //欠费通知
    String ArrearageInform = HOST_VALUE + "appLogin/ArrearageInform";
    //开门
    String openDoor = HOST_VALUE + "appLogin/openDoor";
    //通知列表
    String selectMessageInformAll = HOST_VALUE + "appLogin/selectMessageInformAll";
    //通知详情
    String selectMessageInformOne = HOST_VALUE + "appLogin/selectMessageInformOne";
    //投诉建议
    String ComplaintProposalApplication = HOST_VALUE + "appLogin/ComplaintProposalApplication";
    //申请报修
    String repairApplication = HOST_VALUE + "appLogin/repairApplication";
    //检测更新
    String checkUpdates = HOST_VALUE + "appLogin/checkUpdates";


    
    
    
    
    
    
    
    
    
    
    
    String appUserId = "appUserId";

    int ACCOUNT_EXCEPTION = 999;
    String token_key = "token";
    String token_value = "43378e1b35ae7858e82eba2b27ddefd7";
    String m_key = "&m=";
    String a_key = "&a=";
    String type_key = "type";

    String uuid_key = "uuid";
    //注册
    String app_user_regist = HOST_VALUE + "app_user_regist";

    String shopid_key = "shopid";
    String groupid_key = "GROUP_ID";
    String eid_key = "eid";
    String surperid_key = "superid";
    String managerid_key = "MANAGER_ID";

    String pagetype_key = "pagetype";
    String pagetime_key = "pagetime";
    String page_key = "page";

    String pagenumber_key = "pagenumber";
    String pagenumber_value = "10";

    String orderid_key = "orderid";
    String order_m_value = "OrderManager";
    String getOrder_m_values = "Order";
    String type_up = "up";
    String type_down = "down";
    String type_null = "null";
    String appUserAccount = "appUserAccount";
    String appPassword = "appPassword";


    //上传头像 
    String app_upload_img = HOST_VALUE + "app_upload_img";
    //修改昵称 //性别
    String app_edit_info = HOST_VALUE + "app_edit_info";
    //添加收货地址
    String app_add_address = HOST_VALUE + "app_add_address";
    //查询收货地址
    String get_address_list = HOST_VALUE + "app_select_address";
    //编辑收货地址
    String app_edit_address = HOST_VALUE + "app_edit_address";
    //删除收货地址
    String app_delete_address = HOST_VALUE + "app_delete_address";

}

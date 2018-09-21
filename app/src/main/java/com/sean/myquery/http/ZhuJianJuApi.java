package com.sean.myquery.http;

import com.sean.myquery.bean.BaseBean;
import com.sean.myquery.bean.QuLunhouBean;
import com.sean.myquery.bean.ShiLunhouBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/*****************************************
 * @description:
 * @author:lixiaohui
 * @date: 2017/9/18
 * @company:深圳动态网络科技有限公司
 *****************************************/
public interface ZhuJianJuApi {

    String ROOT_PATH="http://bzflh.szjs.gov.cn";
    //"pageNumber=" + (i + 1) + "&pageSize="+ pageSize
//		 + "&waittype=2&num=0&shoulbahzh=&xingm="+searchXingming
//		 +"&idcard="+searchId;

    /**
     * 获得市轮候数据
     * @param pageNumber
     * @param pageSize
     * @param waittype
     * @param num
     * @param shoulbahzh
     * @param xingm
     * @param idcard
     * @return
     */
    @POST("/TylhW/lhmcAction.do?method=queryYgbLhmcList")
    Observable<BaseBean<List<ShiLunhouBean>>> getShiLunhou(@Query("pageNumber") int pageNumber, @Query("pageSize") int pageSize, @Query("waittype") int waittype, @Query("num") int num, @Query("shoulbahzh") String shoulbahzh, @Query("xingm") String xingm, @Query("idcard") String idcard);
//&pageNumber=1&pageSize=10&waittype=2&num=0&shoulbahzh=&xingm=&idcard=
    /**
     * 获得区轮候
     * @param pageNumber
     * @param pageSize
     * @param waitTpye
     * @param bahzh
     * @param xingm
     * @param sfz
     * @return
     */
    @FormUrlEncoded
    @POST("/TylhW/lhmcAction.do?method=queryLhmcInfo1")
    Observable<BaseBean<List<QuLunhouBean>>> getQuLunhou(@Field("pageNumber") int pageNumber, @Field("pageSize") int pageSize, @Field("waitTpye") int waitTpye, @Field("bahzh") String bahzh, @Field("xingm") String xingm, @Field("sfz") String sfz);

    @POST("/TylhW/lhmcAction.do?method=queryLhmcInfo1")
    Observable<BaseBean<List<QuLunhouBean>>> getQuLunhou(@Query("waitTpye") int waitTpye, @Query("bahzh") String bahzh, @Query("xingm") String xingm, @Query("sfz") String sfz);
//    pageNumber=1&pageSize=10&waitTpye=2&bahzh=BHR00072854&xingm=%25E6%259D%258E%25E6%25A0%25A1%25E8%25BE%2589&sfz=431023198904215859
}

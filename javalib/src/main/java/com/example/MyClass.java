package com.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MyClass {
    public static void main(String[] args) {

        String jsonStr="[\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3635682\",\n" +
                "        \"LHMC_ID\": \"1735867\",\n" +
                "        \"NUM\": \"1\",\n" +
                "        \"NUM_PAIX\": 1,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 1,\n" +
                "        \"RUHSJ\": \"1945-03-03\",\n" +
                "        \"SFZH\": \"44032119450303****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-03\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0026770\",\n" +
                "        \"SQB_ID\": \"118372\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"钟有琼\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3618422\",\n" +
                "        \"LHMC_ID\": \"1735868\",\n" +
                "        \"NUM\": \"1\",\n" +
                "        \"NUM_PAIX\": 2,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 2,\n" +
                "        \"RUHSJ\": \"1953-07-29\",\n" +
                "        \"SFZH\": \"44030119530729****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-12\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0008301\",\n" +
                "        \"SQB_ID\": \"99032\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"蔡主儿\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3626015\",\n" +
                "        \"LHMC_ID\": \"1735869\",\n" +
                "        \"NUM\": \"1\",\n" +
                "        \"NUM_PAIX\": 3,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 3,\n" +
                "        \"RUHSJ\": \"1954-06-27\",\n" +
                "        \"SFZH\": \"44032119540627****\",\n" +
                "        \"SHOUCCBSJ\": \"2003-11\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0005321\",\n" +
                "        \"SQB_ID\": \"96038\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"邝锡雄\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3618896\",\n" +
                "        \"LHMC_ID\": \"1735870\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 4,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 4,\n" +
                "        \"RGQK\": \";签订认购协议书后未在规定时间内签订买卖合同1次\",\n" +
                "        \"RUHSJ\": \"1973-02-05\",\n" +
                "        \"SFZH\": \"44032119351215****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-01\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0025792\",\n" +
                "        \"SQB_ID\": \"117386\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"蔡高松\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3631476\",\n" +
                "        \"LHMC_ID\": \"1735871\",\n" +
                "        \"NUM\": \"1\",\n" +
                "        \"NUM_PAIX\": 5,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 5,\n" +
                "        \"RUHSJ\": \"1955-07-15\",\n" +
                "        \"SFZH\": \"44030119550715****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-12\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0013157\",\n" +
                "        \"SQB_ID\": \"104790\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"黄玉兰\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3633917\",\n" +
                "        \"LHMC_ID\": \"1735872\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 6,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 6,\n" +
                "        \"REMARK\": \"原未成年子女现已成年，不再作为共同申请人\",\n" +
                "        \"RUHSJ\": \"1956-02-08\",\n" +
                "        \"SFZH\": \"44030119560208****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-12\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0008820\",\n" +
                "        \"SQB_ID\": \"99541\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"方伟强\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3630224\",\n" +
                "        \"LHMC_ID\": \"1735873\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 7,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 7,\n" +
                "        \"RUHSJ\": \"1961-03-06\",\n" +
                "        \"SFZH\": \"44032119581017****\",\n" +
                "        \"SHOUCCBSJ\": \"1985-09\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0015542\",\n" +
                "        \"SQB_ID\": \"107166\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"叶建华\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3634053\",\n" +
                "        \"LHMC_ID\": \"1735874\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 8,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 8,\n" +
                "        \"RUHSJ\": \"1961-03-03\",\n" +
                "        \"SFZH\": \"44032119610303****\",\n" +
                "        \"SHOUCCBSJ\": \"1992-08\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0014546\",\n" +
                "        \"SQB_ID\": \"106173\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"罗荣华\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3630182\",\n" +
                "        \"LHMC_ID\": \"1735875\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 9,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 9,\n" +
                "        \"RUHSJ\": \"1979-06-01\",\n" +
                "        \"SFZH\": \"44030119580530****\",\n" +
                "        \"SHOUCCBSJ\": \"1986-01\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0013938\",\n" +
                "        \"SQB_ID\": \"105566\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"梁容娣\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"LHCYXXB_ID\": \"3615950\",\n" +
                "        \"LHMC_ID\": \"1735876\",\n" +
                "        \"NUM\": \"2\",\n" +
                "        \"NUM_PAIX\": 10,\n" +
                "        \"OUTLH_FLAG\": \"0\",\n" +
                "        \"PAIX\": 10,\n" +
                "        \"REMARK\": \"原未成年子女现已成年，不再作为共同申请人\",\n" +
                "        \"RUHSJ\": \"1961-08-10\",\n" +
                "        \"SFZH\": \"44030119610810****\",\n" +
                "        \"SHOUCCBSJ\": \"1987-03\",\n" +
                "        \"SHOUCCBSJ_AJ\": \"五年以上\",\n" +
                "        \"SHOUCCBSJ_GZ\": \"3年以上\",\n" +
                "        \"SHOULHZH\": \"SQA0014156\",\n" +
                "        \"SQB_ID\": \"105783\",\n" +
                "        \"WAIT_TPYE\": \"1\",\n" +
                "        \"XINGM\": \"张凤珠\"\n" +
                "    }\n" +
                "]";

        Gson gson = new Gson();
        List<ShiLunhouBean> datas = gson.fromJson(jsonStr, new TypeToken<List<ShiLunhouBean>>() {
        }.getType());
        System.out.println(datas);
    }
}

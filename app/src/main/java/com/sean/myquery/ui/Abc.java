package com.sean.myquery.ui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*****************************************
 * @description:
 * @author:lixiaohui
 * @date: 2018/3/24
 * @company:深圳动态网络科技有限公司
 ***************************************/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Abc
{
    private int age;
    private boolean isShare;
}

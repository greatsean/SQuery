package com.sean.myquery.bean;

/*****************************************
 * @description:基类
 * @author:lixiaohui
 * @date: 2017/9/19
 * @company:深圳动态网络科技有限公司
 *****************************************/
public class BaseBean<T> {
    private int total;
    private T rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}

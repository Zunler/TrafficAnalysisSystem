/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MonitorFlow
 * Author:   pengzijun
 * Date:     2020/4/21 11:40 上午
 * Description: 每个卡口的车流量具体信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.model;

/**
 * 〈一句话功能简述〉<br>
 * 〈总车流量具体信息〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
public class CarFlow {

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

}
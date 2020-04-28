/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AreaFlow
 * Author:   pengzijun
 * Date:     2020/4/21 11:38 上午
 * Description: 各区域的车流量具体信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.model;

/**
 * 〈一句话功能简述〉<br>
 * 〈各区域的车流量具体信息〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
public class AreaFlow {
    private int id;
    private String monitor_time;
    private int area_id;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonitor_time() {
        return monitor_time;
    }

    public void setMonitor_time(String monitor_time) {
        this.monitor_time = monitor_time;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
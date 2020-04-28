/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: AnalyzeCarByLicense
 * Author:   pengzijun
 * Date:     2020/4/21 11:44 上午
 * Description: 统计各牌照车辆数量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.model;

/**
 * 〈一句话功能简述〉<br> 
 * 〈统计各牌照车辆数量〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
public class AnalyzeCarByLicense {

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private  int id;
    private  String monitor_time;
    private String area;
    private  int count;
}
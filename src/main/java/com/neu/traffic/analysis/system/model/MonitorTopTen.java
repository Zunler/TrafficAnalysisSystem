/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: MonitorTopTen
 * Author:   pengzijun
 * Date:     2020/4/21 11:42 上午
 * Description: 每个卡口车速最快前十名信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.model;

/**
 * 〈一句话功能简述〉<br> 
 * 〈每个卡口车速最快前十名信息〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
public class MonitorTopTen {
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

    public int getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(int monitor_id) {
        this.monitor_id = monitor_id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private int id;
    private  String monitor_time;
    private int monitor_id;
    private String license;
    private double speed;


}
/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TrafficMapper
 * Author:   pengzijun
 * Date:     2020/4/21 11:57 上午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.mapper;

import com.neu.traffic.analysis.system.model.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
@Component
public interface TrafficMapper {
    /***
     * 获取一小时内的牌照分类信息
     */

    @Select("select * from analyze_car_license where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<AnalyzeCarByLicense> getAnalyzeCarByLicense();
    /***
     * 获取一小时内的区域area分类信息
     */

    @Select("select * from area_flow where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<AreaFlow> getAreaFlow();
    /***
     * 获取一小时内的总车流量信息
     */

    @Select("select sum(count) as count from area_flow where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<CarFlow> getCarFlow();

    /***
     * 获取一小时内的总车流量信息
     */
    @Select("select sum(count) as count from area_flow ")
    List<CarFlow> getTotalCarFlow();
    /***
     * 获取一小时内的卡口分类信息
     */

    @Select("select * from monitor_flow where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<MonitorFlow> getMonitorFlow();
    /***
     * 获取一小时内的车速前十信息
     */

    @Select("select * from monitor_top_ten where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<MonitorTopTen> getMonitorTopTen();
    /***
     * 获取一小时内的超速信息
     */

    @Select("select *  from over_speed_car where monitor_time > DATE_SUB(NOW(),INTERVAL  1 HOUR)")
    List<OverSpeedCar> getOverSpeedCar();
}
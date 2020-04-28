/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TrafficService
 * Author:   pengzijun
 * Date:     2020/4/21 12:00 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.service;

import com.neu.traffic.analysis.system.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
@Service
public interface TrafficService {
    List<AnalyzeCarByLicense> getAnalyzeCarByLicense();

    List<AreaFlow> getAreaFlow();

    List<CarFlow> getCarFlow();

    List<CarFlow> getTotalCarFlow();

    List<MonitorFlow> getMonitorFlow();

    List<MonitorTopTen> getMonitorTopTen();

    List<OverSpeedCar> getOverSpeedCar();
}
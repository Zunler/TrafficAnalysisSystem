/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: TrafficServiceImpl
 * Author:   pengzijun
 * Date:     2020/4/21 12:02 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.serviceImpl;

import com.neu.traffic.analysis.system.mapper.TrafficMapper;
import com.neu.traffic.analysis.system.model.*;
import com.neu.traffic.analysis.system.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author pengzijun
 * @create 2020/4/21
 * @since 1.0.0
 */
@Repository
public class TrafficServiceImpl implements TrafficService {
    @Autowired
    private TrafficMapper trafficMapper;

    @Override
    public List<AnalyzeCarByLicense> getAnalyzeCarByLicense() {
        return trafficMapper.getAnalyzeCarByLicense();
    }

    @Override
    public List<AreaFlow> getAreaFlow() {
        return trafficMapper.getAreaFlow();
    }

    @Override
    public List<CarFlow> getCarFlow() {
        return trafficMapper.getCarFlow();
    }

    @Override
    public List<CarFlow> getTotalCarFlow() {
        return trafficMapper.getTotalCarFlow();
    }

    @Override
    public List<MonitorFlow> getMonitorFlow() {
        return trafficMapper.getMonitorFlow();
    }

    @Override
    public List<MonitorTopTen> getMonitorTopTen() {
        return trafficMapper.getMonitorTopTen();
    }

    @Override
    public List<OverSpeedCar> getOverSpeedCar() {
        return trafficMapper.getOverSpeedCar();
    }
}
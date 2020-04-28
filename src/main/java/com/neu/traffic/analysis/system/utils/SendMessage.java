/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: SendMessage
 * Author:   pengzijun
 * Date:     2020/4/4 6:43 下午
 * Description: 发送新增数据
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.utils;

/**
 * 〈一句话功能简述〉<br>
 * 〈发送新增数据〉
 *
 * @author pengzijun
 * @create 2020/4/4
 * @since 1.0.0
 */

import com.neu.traffic.analysis.system.model.*;
import com.neu.traffic.analysis.system.serviceImpl.TrafficServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.websocket.Session;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;


public class SendMessage implements Runnable {


    private Session session;


    private int[] currentIndex;
    TrafficServiceImpl trafficService;

    public SendMessage(Session session) {

        this.session = session;


        trafficService = ApplicationContextProvider.getBean(TrafficServiceImpl.class);

        currentIndex = new int[6];

    }
    public String transform(String time){
        return  time.split("\\.")[0];
    }


    @Override
    public void run() {


        while (true) {
            //检测数据库增量信息
            List<AnalyzeCarByLicense> analyzeCarByLicense = trafficService.getAnalyzeCarByLicense();
            List<AreaFlow> areaFlow = trafficService.getAreaFlow();
            List<CarFlow> carFlow = trafficService.getCarFlow();
            List<CarFlow> totalCarFlow = trafficService.getTotalCarFlow();
            List<MonitorFlow> monitorFlow = trafficService.getMonitorFlow();
            List<MonitorTopTen> monitorTopTen = trafficService.getMonitorTopTen();
            List<OverSpeedCar> overSpeedCar = trafficService.getOverSpeedCar();

            JSONObject res = new JSONObject(new LinkedHashMap());
            //有新增数据就发送到前端
            //车牌分类数据
            if (analyzeCarByLicense != null && currentIndex[0] < analyzeCarByLicense.size()) {
                JSONObject analyzeCarByLicenseJson = new JSONObject(new LinkedHashMap());
                for (int i = currentIndex[0]; i < analyzeCarByLicense.size(); i++) {
                    AnalyzeCarByLicense item = analyzeCarByLicense.get(i);
                    try {
                        analyzeCarByLicenseJson.put(item.getArea(), item.getCount());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    res.put("analyzeCarByLicense", analyzeCarByLicenseJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                currentIndex[0] = analyzeCarByLicense.size();
            }

            //area分类数据

            if (areaFlow != null && currentIndex[1] < areaFlow.size()) {
                JSONObject areaFlowJson = new JSONObject(new LinkedHashMap());
                int count = 0;
                for (int i = currentIndex[1]; i < areaFlow.size(); i++) {
                    count++;
                    //只获取最新8个区域的数据
                    if (count > 8) {
                        break;
                    }
                    AreaFlow item = areaFlow.get(i);
                    try {
                        areaFlowJson.put(String.valueOf(item.getArea_id()), item.getCount());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    res.put("areaFlow", areaFlowJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                currentIndex[1] = areaFlow.size();
            }

            //总车流量数据

            if (totalCarFlow!=null&&totalCarFlow.size()!= 0 ){
                CarFlow item = totalCarFlow.get(0);
                try {
                    res.put("totalCarFlow", item.getCount());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            if (carFlow!=null&&carFlow.size()!= 0) {

                CarFlow item = carFlow.get(0);
                System.out.println(item==null);
                try {
                    res.put("carFlow", item.getCount());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            //卡口分类数据

            if (monitorFlow != null && currentIndex[3] < monitorFlow.size()) {
                JSONObject monitorFlowJson = new JSONObject();
                int count = 0;

                for (int i = currentIndex[3]; i < monitorFlow.size(); i++) {
                    count++;
                    //只获取最新9个卡口的数量
                    if (count > 9) {
                        break;
                    }

                    JSONObject itemJson = new JSONObject();
                    MonitorFlow item = monitorFlow.get(i);
                    //向前端发送json

                    try {


                        monitorFlowJson.put(String.valueOf(item.getMonitor_id()), item.getCount());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    res.put("monitorFlow", monitorFlowJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                currentIndex[3] = monitorFlow.size();
            }
            //车速前十数据
            if (monitorTopTen != null && currentIndex[4] < monitorTopTen.size()) {
                JSONObject monitorTopTenJson = new JSONObject();
                for (int i = currentIndex[4]; i < monitorTopTen.size(); i++) {
                    JSONObject itemJson = new JSONObject();
                    MonitorTopTen item = monitorTopTen.get(i);

                    try {
                        itemJson.put("monitor_time",transform( item.getMonitor_time()));
                        itemJson.put("monitor_id", item.getMonitor_id());
                        itemJson.put("license", item.getLicense());
                        itemJson.put("speed", item.getSpeed());
                        monitorTopTenJson.put(String.valueOf(item.getId()), itemJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    res.put("monitorTopTen", monitorTopTenJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                currentIndex[4] = monitorTopTen.size();
            }
            //超速车辆数据
            if (overSpeedCar != null && currentIndex[5] < overSpeedCar.size()) {
                JSONObject overSpeedCarJson = new JSONObject();
                for (int i = currentIndex[5]; i < overSpeedCar.size(); i++) {
                    OverSpeedCar item = overSpeedCar.get(i);
                    JSONObject itemJson = new JSONObject();

                    //向前端发送json
                    try {
                        itemJson.put("monitor_time", transform(item.getMonitor_time()));
                        itemJson.put("monitor_id", item.getMonitor_id());
                        itemJson.put("camera_id", item.getCamera_id());
                        itemJson.put("license", item.getLicense());
                        itemJson.put("speed", item.getSpeed());
                        itemJson.put("road_id", item.getRoad_id());
                        itemJson.put("area_id", item.getArea_id());
                        overSpeedCarJson.put(String.valueOf(item.getId()), itemJson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    res.put("overSpeedCar", overSpeedCarJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                currentIndex[5] = overSpeedCar.size();
            }

            try {
                System.out.println(res);
                session.getBasicRemote().sendText(String.valueOf(res));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

                //3秒刷新一次

                Thread.sleep(3000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }


        }

    }

}
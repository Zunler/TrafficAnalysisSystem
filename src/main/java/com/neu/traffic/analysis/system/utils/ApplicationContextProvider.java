/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ApplicationContextProvider
 * Author:   pengzijun
 * Date:     2020/4/4 4:45 下午
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.neu.traffic.analysis.system.utils;

/**
 * 〈多线程场景下，使用默认的spring自动装配无法获取bean对象，此方案可以从context上下文中直接获取bean〉<br>
 * 〈〉
 *
 * @author pengzijun
 * @create 2020/4/4
 * @since 1.0.0
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Date: 2018/1/11 0011
 * Time: 13:20
 * To change this template use File | Settings | File Templates.
 * @author pengzijun
 */

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    private ApplicationContextProvider(){

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public  static <T> T getBean(Class<T> aClass){
        return context.getBean(aClass);
    }
}
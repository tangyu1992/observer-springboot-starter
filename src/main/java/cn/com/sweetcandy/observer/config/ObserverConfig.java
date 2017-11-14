/**
 * 
 */
package com.yuntongxun.event.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import com.yuntongxun.event.handels.Handel;
import com.yuntongxun.event.observer.Observer;
import com.yuntongxun.event.utils.ReflectUtils;

/**
 * @author tangyu
 * @date 2017年11月14日 下午4:03:50
 */
@Order(9999)
@Configuration
@ConditionalOnClass(Observer.class)
@EnableConfigurationProperties(ObserverConfigProperties.class)
public class ObserverConfig {
	@Autowired
	private ObserverConfigProperties observerConfigProperties;
	@Autowired
	ApplicationContext context;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "observer", value = "enabled", havingValue = "true")
	public Observer observer() {

		final Observer observer = Observer.getInstance();
		final String observerPackage = observerConfigProperties.getObserverPackage();
		final List<Class> clazzs = ReflectUtils.getAllClassByInterface(observerPackage, Handel.class);
		clazzs.forEach(clazz -> {
			Handel handel = (Handel) context.getBean(clazz);
			if (!StringUtils.isEmpty(handel)) {
				System.out.println("注册" + handel + ",类型：" + handel.handelType());
				observer.regist(handel.handelType(), handel);
			}
		});
		return observer;
	}

}

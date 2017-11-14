/**
 * 
 */
package com.yuntongxun.event.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tangyu
 * @date 2017年11月14日 下午4:06:31
 */
@ConfigurationProperties("observer")
public class ObserverConfigProperties {
	private String observerPackage;

	public String getObserverPackage() {
		return observerPackage;
	}

	public void setObserverPackage(String observerPackage) {
		this.observerPackage = observerPackage;
	}

	@Override
	public String toString() {
		return "ObserverConfigProperties [observerPackage=" + observerPackage + "]";
	}
}

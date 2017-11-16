/**
 * 
 */
package com.yuntongxun.event.observer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;
import com.yuntongxun.event.Event;
import com.yuntongxun.event.handels.Handel;

/**
 * @author tangyu
 * @date 2017年11月14日 下午3:27:27
 */
public class Observer {
	private static Observer instance = null;
	private static final Map<String, Handel<Event>> HANDELS = new HashMap<>();

	private Observer() {
		super();
	}

	private synchronized static Observer syncGetInstance() {
		return new Observer();
	}

	public static Observer getInstance() {
		if (null == instance) {
			return syncGetInstance();
		} else {
			return instance;
		}
	}

	/****
	 * 注册到观察者
	 * 
	 * @param handelType
	 * @param handel
	 */
	public synchronized void regist(String handelType, Handel<Event> handel) {
		if (StringUtils.isEmpty(handelType)) {
			return;
		}
		if (StringUtils.isEmpty(handel)) {
			return;
		}
		HANDELS.putIfAbsent(handelType, handel);
	}

	/****
	 * 从观察者注销
	 * 
	 * @param handel
	 */
	public synchronized void unRegist(Handel<Event> handel) {
		if (StringUtils.isEmpty(handel)) {
			return;
		}
		final String handelType = handel.handelType();
		if (StringUtils.isEmpty(handelType)) {
			return;
		}
		HANDELS.remove(handelType);
	}

	public void handel(final Event event) {
		if (StringUtils.isEmpty(event)) {
			return;
		}
		final String enevtType = event.eventType();
		if (StringUtils.isEmpty(enevtType)) {
			return;
		}
		Handel<Event> handel = HANDELS.get(enevtType);
		if (!StringUtils.isEmpty(handel)) {
			handel.handel(event);
		}
	}

}

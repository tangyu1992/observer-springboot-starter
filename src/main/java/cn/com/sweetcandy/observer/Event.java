/**
 * 
 */
package com.yuntongxun.event;

/**
 * 事件祖先接口，任何想要被观察者处理的事件都应该实现此接口
 * 
 * @author tangyu
 * @date 2017年11月14日 下午2:40:30
 */
public interface Event {
	/****
	 * 对外暴露事件的类型
	 * 
	 * @return
	 */
	public String eventType();

}

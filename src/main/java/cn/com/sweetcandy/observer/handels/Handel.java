/**
 * 
 */
package com.yuntongxun.event.handels;

import com.yuntongxun.event.Event;

/**
 * 处理类祖先接口，所有的处理类都需要实现此接口
 * 
 * @author tangyu
 * @date 2017年11月14日 下午2:46:39
 */
public interface Handel<T extends Event> {
	/****
	 * 获取处理的事件类型
	 * 
	 * @return
	 */
	public String handelType();

	/****
	 * 处理事件
	 * 
	 * @return
	 */
	public Object handel(T t);
}

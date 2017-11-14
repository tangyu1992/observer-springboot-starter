/**
 * 
 */
package com.yuntongxun.event.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Stream;

/**
 * 反射工具类
 * 
 * @author tangyu
 * @date 2017年11月14日 下午4:29:20
 */
public class ReflectUtils {
	/****
	 * 获取某接口的所有实现类
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getAllClassByInterface(String packagePath, Class clazz) {
		final List<Class> result = new ArrayList<>();
		// 判断是否是一个接口
		if (clazz.isInterface()) {
			try {
				/**
				 * 循环判断路径下的所有类是否实现了指定的接口 并且排除接口类自己
				 */
				getAllClass(packagePath).forEach(searchClazz -> {
					if (clazz.isAssignableFrom(searchClazz)) {
						if (!clazz.equals(searchClazz)) {// 自身并不加进去
							result.add(searchClazz);
						} else {

						}
					}
				}

				);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		}
		return result;
	}

	/**
	 * 从一个指定路径下查找所有的类
	 * 
	 * @param name
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> getAllClass(String packagename) {
		List<Class> result = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packagename.replace('.', '/');
		try {
			/**
			 * 这里面的路径使用的是相对路径 如果大家在测试的时候获取不到，请理清目前工程所在的路径 使用相对路径更加稳定！
			 * 另外，路径中切不可包含空格、特殊字符等！ 本人在测试过程中由于空格，吃了大亏！！！
			 */
			Enumeration<URL> enumeration = classLoader.getResources(path);
			while (enumeration.hasMoreElements()) {
				URL url = enumeration.nextElement();
				result.addAll(findClass(new File(url.getFile()), packagename));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 如果file是文件夹，则递归调用findClass方法，或者文件夹下的类 如果file本身是类文件，则加入list中进行保存，并返回
	 * 
	 * @param file
	 * @param packagename
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClass(File file, String packagename) {
		List<Class> result = new ArrayList<>();
		if (!file.exists()) {
			return result;
		}
		Stream.of(file.listFiles()).forEach(foldFile -> {
			if (foldFile.isDirectory()) {
				assert !foldFile.getName().contains(".");// 添加断言用于判断
				List<Class> tmpResult = findClass(foldFile, packagename + "." + foldFile.getName());
				result.addAll(tmpResult);
			} else if (foldFile.getName().endsWith(".class")) {
				try {
					// 保存的类文件不需要后缀.class
					result.add(Class.forName(
							packagename + '.' + foldFile.getName().substring(0, foldFile.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		return result;
	}

}

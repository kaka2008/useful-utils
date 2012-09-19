/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: ReflectionUtils.java 384 2009-08-28 14:50:14Z calvinxiu $
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * �����Utils��������.
 * 
 * �ṩ����˽�б���,��ȡ��������Class,��ȡ������Ԫ�ص����Ե�Utils����.
 * 
 * @author calvin
 */
public class ReflectionUtils {

	private static Log logger = LogFactory.getLog(ReflectionUtils.class);

	/**
	 * ֱ�Ӷ�ȡ��������ֵ,����private/protected���η�,������getter����.
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);
		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("�������׳����쳣{}", e);
		}
		return result;
	}

	/**
	 * ֱ�����ö�������ֵ,����private/protected���η�,������setter����.
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("�������׳����쳣:{}", e);
		}
	}

	/**
	 * ֱ�ӵ��ö��󷽷�,����private/protected���η�.
	 */
	public static Object invokeMethod(final Object object,
			final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null)
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + object + "]");

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			logger.error("�������׳����쳣:{}", e);
		}

		return null;
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object����Ϊ��");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			// System.out.println("class:" + superClass);
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field���ڵ�ǰ�ඨ��,��������ת��
				// e.printStackTrace();
				// continue;
			}
		}
		return null;
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredField.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * ѭ������ת��,��ȡ�����DeclaredMethod.
	 */
	protected static Method getDeclaredMethod(Object object, String methodName,
			Class<?>[] parameterTypes) {
		Assert.notNull(object, "object����Ϊ��");

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method���ڵ�ǰ�ඨ��,��������ת��
			}
		}
		return null;
	}

	/**
	 * ͨ������,���Class�����������ĸ���ķ��Ͳ���������. eg. public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * ͨ������,��ö���Classʱ�����ĸ���ķ��Ͳ���������.
	 * 
	 * ��public UserDao extends HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */

	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * ��ȡ�����еĶ��������(ͨ��getter����),��ϳ�List.
	 * 
	 * @param collection
	 *            ��Դ����.
	 * @param propertyName
	 *            Ҫ��ȡ��������.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection,
			final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			convertToUncheckedException(e);
		}

		return list;
	}

	/**
	 * ��ȡ�����еĶ��������(ͨ��getter����),��ϳ��ɷָ���ָ����ַ���.
	 * 
	 * @param collection
	 *            ��Դ����.
	 * @param propertyName
	 *            Ҫ��ȡ��������.
	 * @param separator
	 *            �ָ���.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * ת���ַ������͵�clazz��property���͵�ֵ.
	 * 
	 * @param value
	 *            ��ת�����ַ���
	 * @param clazz
	 *            �ṩ������Ϣ��Class
	 * @param propertyName
	 *            �ṩ������Ϣ��Class������.
	 */
	public static Object convertValue(Object value, Class<?> toType) {
		try {
			DateConverter dc = new DateConverter();
			dc.setUseLocaleFormat(true);
			dc
					.setPatterns(new String[] { "yyyy-MM-dd",
							"yyyy-MM-dd HH:mm:ss" });
			ConvertUtils.register(dc, Date.class);
			return ConvertUtils.convert(value, toType);
		} catch (Exception e) {
			throw convertToUncheckedException(e);
		}
	}

	/**
	 * ������ʱ��checked exceptionת��Ϊunchecked exception.
	 */
	public static IllegalArgumentException convertToUncheckedException(
			Exception e) {
		if (e instanceof IllegalAccessException
				|| e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException)
			return new IllegalArgumentException("Refelction Exception.", e);
		else
			return new IllegalArgumentException(e);
	}

	/**
	 * �ж�beanClass�Ƿ�ע����ָ��Annotation
	 * 
	 * @return
	 */
	public static boolean isAnnotationPresent(String beanClass, Class annotation) {
		ClassLoader classLoader = annotation.getClassLoader();
		Class clz = null;
		try {
			clz = classLoader.loadClass(beanClass);
		} catch (ClassNotFoundException e) {
			logger.warn("�޷��ҵ��ࣺ" + beanClass);
			return false;
		}
		return clz.isAnnotationPresent(annotation);
	}
}

package com.twb.robot.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Spring工具类
 *
 * @author zsm
 * @date 2015-4-10
 */
@Component
public class SpringUtil implements ApplicationContextAware, InitializingBean, ApplicationListener<ContextRefreshedEvent> {

	@Autowired   
	private static ApplicationContext applicationContext;

    /**
     * 设置上下文
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 返回上下文
     *
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends ApplicationContext> T getApplicationContext() {
        return (T) applicationContext;
    }

    /**
     * 根据bean的name返回bean实例
     *
     * @param <T>
     * @param name
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) getApplicationContext().getBean(name);
    }

    /**
     * 根据bean的name返回bean实例
     *
     * @param <T>
     * @param name
     * @return
     * @throws BeansException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBeanByClazzPath(String clazzPath) throws BeansException, ClassNotFoundException {
        return (T) getApplicationContext().getBean(Class.forName(clazzPath));
    }

    /**
     * 根据bean的name和class类型返回bean实例
     *
     * @param <T>
     * @param name
     * @param requiredType
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getApplicationContext().getBean(name, requiredType);
    }

    /**
     * 根据class类型返回bean实例
     *
     * @param <T>
     * @param requiredType
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return getApplicationContext().getBean(requiredType);
    }

    /**
     * BeanFactory是否包含此name的实例
     *
     * @param name
     * @return
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * 根据bean的name判断该bean是否单例
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isSingleton(name);
    }

    /**
     * 根据bean的name判断该bean是否多例
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isPrototype(name);
    }

    /**
     * 根据bean的name判断该bean是否实现了class类型接口
     *
     * @param name
     * @param targetType
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isTypeMatch(name, targetType);
    }

    /**
     * 根据bean的name返回该bean的class类型
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(name);
    }

    /**
     * 根据bean的name返回该bean的别名
     *
     * @param name
     * @return
     */
    public static String[] getAliases(String name) {
        return getApplicationContext().getAliases(name);
    }

    /**
     * 从spring中取bean，没有就注册一个
     *
     * @param <T>
     * @param name
     * @param requiredType
     * @return
     */
    public static <T> T getOrRegisterBean(String name, Class<T> requiredType) {
        if (requiredType == null) {
            return null;
        }

        ConfigurableApplicationContext appContext = getApplicationContext();
        T bean = null;
        try {
            bean = appContext.getBean(name, requiredType);
        }
        catch (NoSuchBeanDefinitionException e) {

        }

        if (bean == null) {
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) appContext.getBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(requiredType);
            beanFactory.registerBeanDefinition(name, beanDefinitionBuilder.getRawBeanDefinition());

            bean = appContext.getBean(name, requiredType);
        }

        return bean;
    }

    /**
     * 从spring中取bean，没有就注册一个，bean的name是类名(首字母小写)
     *
     * @param <T>
     * @param requiredType
     * @return
     */
    public static <T> T getOrRegisterBean(Class<T> requiredType) {
        if (requiredType == null) {
            return null;
        }

        String name = StringUtils.uncapitalize(requiredType.getSimpleName());//首字母小写
        return getOrRegisterBean(name, requiredType);
    }


    private static boolean bAfterInit = false;//是否已经初始化完成了

    /**
     * {@inheritDoc}
     *
     * @author zhouwenqing
     * 2014-4-28 zhouwenqing
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bAfterInit = true;
    }


    public static boolean isInitComplete() {
        return bAfterInit;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

}

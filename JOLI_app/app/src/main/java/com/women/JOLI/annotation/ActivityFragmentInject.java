package com.women.JOLI.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: ActivityFragmentInject<p>
 * Author: oubowu<p>
 * Fuction: Activity、Fragment初始化的用到的注解<p>
 * CreateDate: 2016/2/15 23:30<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityFragmentInject {

    /**
     * 顶部局的id
     *
     * @return
     */
    int contentViewId() default -1;

    /**
     * 菜单id
     *
     * @return
     */
    int menuId() default -1;

    /**
     * 是否开启侧滑
     *
     * @return
     */
    boolean enableSlidr() default false;

    /**
     * 是否存在NavigationView
     *
     * @return
     */
    boolean hasNavigationView() default false;

    /**
     * 是否处理RefreshLayout，对应父布局为CoordinateLayout加AppbarLayout与RefreshLayout造成的事件冲突
     *
     * @return
     */
    boolean handleRefreshLayout() default false;

    /**
     * toolbar的标题id
     *
     * @return
     */
    int toolbarTitle() default -1;

    /**
     * toolbar的菜单按钮
     *
     * @return
     */
    int toolbarIndicator() default -1;

    /**
     * toolbar菜单默认选中项
     *
     * @return
     */
    int menuDefaultCheckedItem() default -1;

}

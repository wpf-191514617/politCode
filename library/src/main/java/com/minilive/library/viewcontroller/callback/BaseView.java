package com.minilive.library.viewcontroller.callback;

/**
 * 作者：王鹏飞
 * 创建时间：2015/12/31 10:23
 * 邮箱：15291967179@163.com
 * 描述：
 */
public interface BaseView
{
    /**
     * loading状态
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏Loading
     */
    void hideLoading();

    /**
     *
     * @param msg
     */
    void showError(String msg);
    
    void showException(String msg);
    
    void showNetError();
    
    void showNormal();
    
}

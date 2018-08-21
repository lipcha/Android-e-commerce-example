package com.foxkiev.app.base;


/**
 * Created by lipcha on 22.02.18.
 */

public interface Router {

    void showRoot();
    void addFragmentToBackStack(BaseFragment fragment);
    boolean popBackStack();
}

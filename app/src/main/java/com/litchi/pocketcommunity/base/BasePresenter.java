package com.litchi.pocketcommunity.base;

import java.lang.ref.WeakReference;

public class BasePresenter<T extends BaseView>{

    private WeakReference<T> weakReference;

    public void onAttach(T view){
        this.weakReference =  new WeakReference<T>(view);
    }

    public void onDetach(){
        weakReference.clear();
        weakReference = null;
    }

    public T getView(){
        if (weakReference != null){
            return weakReference.get();
        }
        return null;
    }
}

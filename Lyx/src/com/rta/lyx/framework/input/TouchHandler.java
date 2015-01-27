package com.rta.lyx.framework.input;

import java.util.List;

import android.view.View.OnTouchListener;

import com.rta.lyx.framework.input.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
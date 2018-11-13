package com.minilive.library.entity;

/**
 * Created by Administrator on 2018/1/8.
 */

public class EventData<T> {

    private int CODE;
    private T Data;

    public EventData(int CODE) {
        this.CODE = CODE;
    }

    public EventData(int CODE, T data) {
        this.CODE = CODE;
        Data = data;
    }

    public int getCODE() {
        return CODE;
    }

    public T getData() {
        return Data;
    }
}

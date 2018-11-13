package com.zodiac.polit.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by john on 2018/10/7.
 */

public class AreaResponse implements Parcelable {


    /**
     * id : 123
     * isNewRecord : false
     * pageNo : 0
     * pageSize : 0
     * name : 保定市
     * parentId : 0
     */

    private String id;
    private boolean isNewRecord;
    private int pageNo;
    private int pageSize;
    private String name;
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsNewRecord() {
        return isNewRecord;
    }

    public void setIsNewRecord(boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.isNewRecord ? (byte) 1 : (byte) 0);
        dest.writeInt(this.pageNo);
        dest.writeInt(this.pageSize);
        dest.writeString(this.name);
        dest.writeString(this.parentId);
    }

    public AreaResponse() {
    }

    protected AreaResponse(Parcel in) {
        this.id = in.readString();
        this.isNewRecord = in.readByte() != 0;
        this.pageNo = in.readInt();
        this.pageSize = in.readInt();
        this.name = in.readString();
        this.parentId = in.readString();
    }

    public static final Parcelable.Creator<AreaResponse> CREATOR = new Parcelable.Creator<AreaResponse>() {
        @Override
        public AreaResponse createFromParcel(Parcel source) {
            return new AreaResponse(source);
        }

        @Override
        public AreaResponse[] newArray(int size) {
            return new AreaResponse[size];
        }
    };
}

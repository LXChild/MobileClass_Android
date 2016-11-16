package com.lxchild.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LXChild on 04/11/2016.
 */

public class PaperBean implements Parcelable {
    private String title;
    private String date;
    private String remark;

    public PaperBean() {
    }

    public PaperBean(String title, String remark, String date) {
        this.title = title;
        this.remark = remark;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.remark);
    }

    protected PaperBean(Parcel in) {
        this.title = in.readString();
        this.date = in.readString();
        this.remark = in.readString();
    }

    public static final Parcelable.Creator<PaperBean> CREATOR = new Parcelable.Creator<PaperBean>() {
        @Override
        public PaperBean createFromParcel(Parcel source) {
            return new PaperBean(source);
        }

        @Override
        public PaperBean[] newArray(int size) {
            return new PaperBean[size];
        }
    };
}

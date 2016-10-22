package com.lxchild.bean;

import java.util.Date;

/**
 * Created by LXChild on 22/10/2016.
 */

public class ClassBean {
    private int mId;
    private String mName;
    private String mTeacher;
    private Date mTime;
    private String mRoom;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTeacher() {
        return mTeacher;
    }

    public void setTeacher(String teacher) {
        mTeacher = teacher;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }
}

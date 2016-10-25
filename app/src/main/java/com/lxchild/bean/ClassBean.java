package com.lxchild.bean;

/**
 * Created by LXChild on 22/10/2016.
 */

public class ClassBean {
    private int mId;
    private String mName;
    private String mTeacher;
    private String mTime;
    private String mRoom;

    public ClassBean(int id, String name, String teacher, String time, String room) {
        mId = id;
        mName = name;
        mTeacher = teacher;
        mTime = time;
        mRoom = room;
    }

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

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
    }
}

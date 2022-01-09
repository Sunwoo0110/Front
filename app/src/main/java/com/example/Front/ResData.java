package com.example.Front;

// resList.json 에는 이미지 안넣음 (추가 구현)
public class ResData {
    //public int img;
    public String name;
    public String place;
    public String time;
    public int like;

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setLike(int like) {
        this.like = like;
    }

}
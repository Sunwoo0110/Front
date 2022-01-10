package com.example.Front;

// resList.json 에는 이미지 안넣음 (추가 구현)
public class ResData {
    public String img;
    public String name;
    public String place;
    public String time;
    public Float score;

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getTime() {
        return time;
    }

    public Float getScore() {return score;}

    public void setImg(String img) {this.img = img;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setScore(float score) { this.score = score; }

}
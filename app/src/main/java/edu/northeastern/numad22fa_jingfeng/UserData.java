package edu.northeastern.numad22fa_jingfeng;

public class UserData {
    private final String name;
    private final String link;

    public UserData(String name, String link) {
        this.name = name;
        this.link = link;
    }
    public String getName(){return name;}
    public String getLink(){return link;}
}

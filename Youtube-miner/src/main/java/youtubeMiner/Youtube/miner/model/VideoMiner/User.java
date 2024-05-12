package youtubeMiner.Youtube.miner.model.VideoMiner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public User(String name, String user_link, String picture_link) {
        this.name = name;
        this.user_link = user_link;
        this.picture_link = picture_link;
    }

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("user_link")
    private String user_link;

    @JsonProperty("picture_link")
    private String picture_link;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_link() {
        return user_link;
    }

    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }
    public String getId(){return id;}
    public void setId(String id){this.id=id;}
    @Override
    public String toString() {
        return "User{" +
                "id='"+id+'\''+
                ",name='" + name + '\'' +
                ", user_link='" + user_link + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }




}

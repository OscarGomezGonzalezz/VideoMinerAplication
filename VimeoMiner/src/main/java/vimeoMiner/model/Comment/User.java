
package vimeoMiner.model.Comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "uri",
    "name",
    "link",
    "capabilities",
    "location",
    "gender",
    "bio",
    "short_bio",
    "created_time",
    "pictures",
    "websites",
    "metadata",
    "location_details",
    "skills",
    "available_for_hire",
    "can_work_remotely",
    "resource_key",
    "account"
})
public class User {

    @JsonProperty("uri")
    private String uri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("link")
    private String link;
    @JsonProperty("pictures")
    private Pictures pictures;
    @JsonProperty("resource_key")
    private String resourceKey;
    @JsonProperty("account")
    private String account;
    public String getId(){
        String [] partes=uri.split("/");
        return partes[partes.length-1];
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String link) {
        this.link = link;
    }
    @JsonProperty("pictures")
    public Pictures getPictures() {
        return pictures;
    }

    @JsonProperty("pictures")
    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }
    @JsonProperty("resource_key")
    public String getResourceKey() {
        return resourceKey;
    }

    @JsonProperty("resource_key")
    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    @JsonProperty("account")
    public String getAccount() {
        return account;
    }

    @JsonProperty("account")
    public void setAccount(String account) {
        this.account = account;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("uri");
        sb.append('=');
        sb.append(((this.uri == null)?"<null>":this.uri));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("link");
        sb.append('=');
        sb.append(((this.link == null)?"<null>":this.link));
        sb.append(',');
        sb.append("capabilities");
        sb.append('=');
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(',');
        sb.append("gender");
        sb.append('=');
        sb.append(',');
        sb.append("bio");
        sb.append('=');
        sb.append(',');
        sb.append("shortBio");
        sb.append('=');
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(',');
        sb.append("pictures");
        sb.append('=');
        sb.append(((this.pictures == null)?"<null>":this.pictures));
        sb.append(',');
        sb.append("websites");
        sb.append('=');
        sb.append(',');
        sb.append("metadata");
        sb.append('=');
        sb.append(',');
        sb.append("locationDetails");
        sb.append('=');
        sb.append(',');
        sb.append("skills");
        sb.append('=');
        sb.append(',');
        sb.append("availableForHire");
        sb.append('=');
        sb.append(',');
        sb.append("canWorkRemotely");
        sb.append('=');
        sb.append(',');
        sb.append("resourceKey");
        sb.append('=');
        sb.append(((this.resourceKey == null)?"<null>":this.resourceKey));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.account == null)?"<null>":this.account));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
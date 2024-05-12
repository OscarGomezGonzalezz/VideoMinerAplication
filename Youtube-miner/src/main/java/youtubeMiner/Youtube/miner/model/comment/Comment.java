
package youtubeMiner.Youtube.miner.model.comment;
import com.fasterxml.jackson.annotation.*;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("createdOn")
    private String createdOn;

    @JsonProperty("snippet")
    private CommentSnippet snippet;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getText() {
        return text;
    }

    @JsonProperty
    public String getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("snippet")
    public CommentSnippet getCommentSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setCommentSnippet(CommentSnippet snippet) {
        this.snippet = snippet;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("snippet");
        sb.append('=');
        sb.append(((this.snippet == null)?"<null>":this.snippet));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

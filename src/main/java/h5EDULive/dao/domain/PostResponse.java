package h5EDULive.dao.domain;

import javax.persistence.Entity;

@Entity
public class PostResponse {
    private int postId;
    private int resId;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

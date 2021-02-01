package com.study.gongteacher.data.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.study.gongteacher.data.dto.Post;
import com.study.gongteacher.data.dto.PostComent;
import com.study.gongteacher.data.network.model.BaseResponse;

import java.util.List;

public class PostResponse extends BaseResponse {


    //게시글 리스트
    public class PostList{
        @Expose
        @SerializedName("post")
        private List<Post> post;

        public List<Post> getPost() {
            return post;
        }
    }

    //댓글 작성
    public class WriteComment{
        @Expose
        @SerializedName("post_coment")
        private PostComent postComent;

        public PostComent getPostComent() {
            return postComent;
        }
    }

    //댓글 수정
    public class UpdateComent{
        @Expose
        @SerializedName("post_coment")
        private PostComent postComent;

        public PostComent getPostComent() {
            return postComent;
        }
    }

    //댓글 삭제
    public class DeleteComent{
        @Expose
        @SerializedName("post_coment")
        private PostComent postComent;

        public PostComent getPostComent() {
            return postComent;
        }
    }

    //댓글 리스트 가져오기
    public class ReadComent{
        @Expose
        @SerializedName("coment_list")
        private List<PostComent> postComentList;

        public List<PostComent> getPostComentList() {
            return postComentList;
        }
    }

}

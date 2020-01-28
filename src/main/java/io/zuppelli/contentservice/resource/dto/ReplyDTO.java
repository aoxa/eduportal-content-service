package io.zuppelli.contentservice.resource.dto;

import java.util.List;

public class ReplyDTO {
    List<OptionDTO> replies;

    public List<OptionDTO> getReplies() {
        return replies;
    }

    public void setReplies(List<OptionDTO> replies) {
        this.replies = replies;
    }
}

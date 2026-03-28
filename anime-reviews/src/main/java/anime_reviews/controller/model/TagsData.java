package anime_reviews.controller.model;

import anime_reviews.entity.Tags;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TagsData {
    private Long tagId;
    @NotBlank(message = "Tag name is required")
    private String tagName;

    public TagsData(Tags tag) {
        this.tagId = tag.getTagId();
        this.tagName = tag.getTagName();
    }

    public TagsData(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Tags toTags() {
        Tags tag = new Tags();

        tag.setTagId(tagId);
        tag.setTagName(tagName);

        return tag;
    }
}

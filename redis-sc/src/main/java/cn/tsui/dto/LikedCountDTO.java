package cn.tsui.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class LikedCountDTO {
    Integer id;

    String key;

    Integer count;

    public LikedCountDTO(String key, Integer count) {
        this.key = key;
        this.count = count;
    }
}

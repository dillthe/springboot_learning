package com.github.supercoding.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ItemBody {
    private String name;
    private String type;
    private Integer price;
    private Spec spec;

}

package com.github.supercoding.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor

public class BuyOrder {
    private Integer itemId;
    private Integer itemNums;
}

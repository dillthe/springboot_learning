package com.github.supercoding.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Spec{
    @ApiModelProperty(name = "cpu", value = "Item CPU", example = "Google Tensor")private String cpu;
    @ApiModelProperty(name = "capacity", value = "Item Capacity", example = "25G")private String capacity;


}

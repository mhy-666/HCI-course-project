package com.hci.mhy.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Test {

    @JsonProperty("a")
    private Integer a;
    @JsonProperty("b")
    private Integer b;
    @JsonProperty("c")
    private List<Integer> c;
}

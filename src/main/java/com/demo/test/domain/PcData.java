package com.demo.test.domain;

import com.demo.common.domain.LongId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PcData extends LongId {
    private String name;
}

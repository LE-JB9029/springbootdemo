package com.demo.common.domain;

import java.io.Serializable;

public interface PK<E extends Serializable> extends Serializable {
    E getId();

    void setId(E var1);
}
package ru.weierstrass.components.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface LinoIdentifiable {

    @JsonIgnore
    String getKey();

}

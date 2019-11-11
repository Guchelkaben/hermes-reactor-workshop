package de.mmeier.reactortesting.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Pokemon {

    private String name;
    private String id;
    private int height;
}

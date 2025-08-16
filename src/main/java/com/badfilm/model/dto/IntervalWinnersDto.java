package com.badfilm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IntervalWinnersDto {
    public String producer;
    public int interval;
    public int previousWin;
    public int followingWin;
}

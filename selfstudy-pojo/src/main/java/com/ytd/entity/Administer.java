package com.ytd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Administer {
    private Long id;
    private String username;
    private String password;
    private Integer status;
}

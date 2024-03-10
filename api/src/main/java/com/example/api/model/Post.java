package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Max Borowski
 */
@Getter
@Setter
public class Post implements Serializable {

    private int userId;
    private int id;
    private String title;
    private String body;

}

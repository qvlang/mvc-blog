package com.lang.blog.model;

import com.lang.blog.centers.IObserve;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserArticleSb implements Serializable {

    private static final Long serialVersionUID = 156464888856l;
    private Long id;
    private Long uid;
    private Long aid;
}

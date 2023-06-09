package com.example.forumsystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/5/4 22:09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {

    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;
    private Date createTime;

}

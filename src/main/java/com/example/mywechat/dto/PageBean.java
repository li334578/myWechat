package com.example.mywechat.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageBean<T> implements Serializable {
    private static final Long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private Integer current;

    /**
     * 起始位置
     */
    private Integer start;

    /**
     * 查询长度
     */
    private Integer size;

    /**
     * 返回数据
     */
    private List<T> data;

    /**
     * 返回总数
     */
    private Integer total;
}

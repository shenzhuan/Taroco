package cn.taroco.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权限表
 *
 * @author liuht
 * @since 2017-11-08
 */
@Data
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 父菜单ID
     */
    private Integer parentId;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0--正常 1--删除
     */
    private String delFlag;

}

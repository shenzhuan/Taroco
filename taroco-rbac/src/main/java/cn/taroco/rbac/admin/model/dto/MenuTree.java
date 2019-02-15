package cn.taroco.rbac.admin.model.dto;

import cn.taroco.common.vo.MenuVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuht
 * @date 2017年11月9日23:33:27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTree extends TreeNode {
    private String icon;
    private String name;
    private String url;
    private boolean spread = false;
    private String path;
    private String label;
    private Integer sort;

    public MenuTree() {
    }

    public MenuTree(MenuVO menuVo) {
        this.id = menuVo.getId();
        this.parentId = menuVo.getParentId();
        this.icon = menuVo.getIcon();
        this.name = menuVo.getName();
        this.url = menuVo.getUrl();
        this.path = menuVo.getPath();
        this.label = menuVo.getName();
        this.sort = menuVo.getSort();
    }
}

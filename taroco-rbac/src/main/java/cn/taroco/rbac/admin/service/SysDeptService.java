package cn.taroco.rbac.admin.service;

import cn.taroco.rbac.admin.model.dto.DeptTree;
import cn.taroco.rbac.admin.model.entity.SysDept;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author liuht
 * @since 2018-01-20
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 查询部门树菜单
     * @param sysDeptEntityWrapper
     * @return 树
     */
    List<DeptTree> selectListTree(QueryWrapper<SysDept> sysDeptEntityWrapper);

    /**
     * 添加信息部门
     * @param sysDept
     * @return
     */
    Boolean insertDept(SysDept sysDept);

    /**
     * 删除部门
     * @param id 部门 ID
     * @return 成功、失败
     */
    Boolean deleteDeptById(Integer id);

    /**
     * 更新部门
     * @param sysDept 部门信息
     * @return 成功、失败
     */
    Boolean updateDeptById(SysDept sysDept);
}

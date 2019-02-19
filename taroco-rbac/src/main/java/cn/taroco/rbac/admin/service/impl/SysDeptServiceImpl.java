package cn.taroco.rbac.admin.service.impl;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.exception.BusinessException;
import cn.taroco.rbac.admin.common.util.TreeUtil;
import cn.taroco.rbac.admin.mapper.SysDeptMapper;
import cn.taroco.rbac.admin.mapper.SysDeptRelationMapper;
import cn.taroco.rbac.admin.model.dto.DeptTree;
import cn.taroco.rbac.admin.model.entity.SysDept;
import cn.taroco.rbac.admin.model.entity.SysDeptRelation;
import cn.taroco.rbac.admin.model.entity.SysRoleDept;
import cn.taroco.rbac.admin.service.SysDeptService;
import cn.taroco.rbac.admin.service.SysRoleDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author liuht
 * @since 2018-01-20
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysDeptRelationMapper sysDeptRelationMapper;

    @Autowired
    private SysRoleDeptService sysRoleDeptService;

    /**
     * 添加信息部门
     *
     * @param dept 部门
     * @return
     */
    @Override
    public Boolean insertDept(SysDept dept) {
        this.save(dept);
        this.insertDeptRelation(dept);
        return Boolean.TRUE;
    }

    /**
     * 维护部门关系
     * @param sysDept 部门
     */
    private void insertDeptRelation(SysDept sysDept) {
        //增加部门关系表
        SysDeptRelation deptRelation = new SysDeptRelation();
        deptRelation.setDescendant(sysDept.getParentId());
        List<SysDeptRelation> deptRelationList = sysDeptRelationMapper.selectList(new QueryWrapper<>(deptRelation));
        for (SysDeptRelation sysDeptRelation : deptRelationList) {
            sysDeptRelation.setDescendant(sysDept.getDeptId());
            sysDeptRelationMapper.insert(sysDeptRelation);
        }
        //自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        sysDeptRelationMapper.insert(own);
    }

    /**
     * 删除部门
     *
     * @param id 部门 ID
     * @return 成功、失败
     */
    @Override
    public Boolean deleteDeptById(Integer id) {
        // 删除前判断部门下面是否包含角色
        final SysRoleDept sysRoleDept = new SysRoleDept();
        sysRoleDept.setDeptId(id);
        final int count = sysRoleDeptService.count(new QueryWrapper<>(sysRoleDept));
        if (count > 0) {
            throw new BusinessException("部门下尚存在角色, 不能删除");
        }
        SysDept sysDept = new SysDept();
        sysDept.setDeptId(id);
        sysDept.setUpdateTime(new Date());
        sysDept.setDelFlag(CommonConstant.STATUS_DEL);
        this.updateById(sysDept);
        sysDeptMapper.deleteDeptRealtion(id);
        return Boolean.TRUE;
    }

    /**
     * 更新部门
     *
     * @param sysDept 部门信息
     * @return 成功、失败
     */
    @Override
    public Boolean updateDeptById(SysDept sysDept) {
        //更新部门状态
        this.updateById(sysDept);
        //删除部门关系
        sysDeptMapper.deleteDeptRealtion(sysDept.getDeptId());
        //新建部门关系
        this.insertDeptRelation(sysDept);
        return null;
    }

    /**
     * 查询部门树
     *
     * @param sysDeptEntityWrapper
     * @return 树
     */
    @Override
    public List<DeptTree> selectListTree(QueryWrapper<SysDept> sysDeptEntityWrapper) {
        return getDeptTree(this.list(sysDeptEntityWrapper), 0);
    }

    /**
     * 构建部门树
     *
     * @param depts 部门
     * @param root  根节点
     * @return
     */
    private List<DeptTree> getDeptTree(List<SysDept> depts, int root) {
        List<DeptTree> trees = new ArrayList<>();
        DeptTree node;
        for (SysDept dept : depts) {
            if (dept.getParentId().equals(dept.getDeptId())) {
                continue;
            }
            node = new DeptTree();
            node.setId(dept.getDeptId());
            node.setParentId(dept.getParentId());
            node.setName(dept.getName());
            node.setOrderNum(dept.getOrderNum());
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }
}

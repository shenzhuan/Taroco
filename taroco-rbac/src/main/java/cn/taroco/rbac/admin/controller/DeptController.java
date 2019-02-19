package cn.taroco.rbac.admin.controller;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.web.BaseController;
import cn.taroco.rbac.admin.model.dto.DeptTree;
import cn.taroco.rbac.admin.model.entity.SysDept;
import cn.taroco.rbac.admin.service.SysDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author liuht
 * @since 2018-01-20
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysDept
     */
    @GetMapping("/{id}")
    public SysDept get(@PathVariable Integer id) {
        return sysDeptService.getById(id);
    }


    /**
     * 返回所有部门列表
     *
     * @return 返回所有部门列表
     */
    @GetMapping(value = "/list")
    public List<SysDept> getAllList() {
        SysDept condition = new SysDept();
        condition.setDelFlag(CommonConstant.STATUS_NORMAL);
        return sysDeptService.list(new QueryWrapper<>(condition));
    }

    /**
     * 返回树形部门集合
     *
     * @return 树形部门
     */
    @GetMapping(value = "/tree")
    public List<DeptTree> getTree() {
        SysDept condition = new SysDept();
        condition.setDelFlag(CommonConstant.STATUS_NORMAL);
        return sysDeptService.selectListTree(new QueryWrapper<>(condition));
    }

    /**
     * 添加
     *
     * @param sysDept 实体
     * @return success/false
     */
    @PostMapping
    public Boolean add(@RequestBody SysDept sysDept) {
        return sysDeptService.insertDept(sysDept);
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return sysDeptService.deleteDeptById(id);
    }

    /**
     * 编辑
     *
     * @param sysDept 实体
     * @return success/false
     */
    @PutMapping
    public Boolean edit(@RequestBody SysDept sysDept) {
        sysDept.setUpdateTime(new Date());
        return sysDeptService.updateDeptById(sysDept);
    }
}

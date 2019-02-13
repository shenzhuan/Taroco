package cn.taroco.gateway.service.impl;

import cn.taroco.gateway.service.PermissionService;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 请求权限判断service
 *
 * @author liuht
 * @date 2017/10/28
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 前端跨域OPTIONS请求预检放行 过前端配置代也可通理实现
        // 在这里放行具有一定风险,也可通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        // TODO 权限拦截需要重写
        boolean hasPermission = true;

        if (principal != null) {
            if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
            }
        }
        return hasPermission;
    }
}

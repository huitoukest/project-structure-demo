package com.example.structure.multibizmodule.org.biz.service;

import com.example.structure.multibizmodule.common.constant.ErrorCode;
import com.example.structure.multibizmodule.common.exception.BizException;
import com.example.structure.multibizmodule.org.api.OrgApi;
import com.example.structure.multibizmodule.org.api.OrgBrief;
import com.example.structure.multibizmodule.org.api.OrgNode;
import com.example.structure.multibizmodule.org.biz.dao.OrgDao;
import com.example.structure.multibizmodule.org.biz.entity.Org;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门域实现：实现 org-api，树组装与父子校验在本类内完成。
 */
@Service
@RequiredArgsConstructor
public class OrgService implements OrgApi {

    private final OrgDao orgDao;

    /**
     * 按主键查询部门，未命中转 404 业务异常。
     *
     * @param id 部门主键
     * @return 部门摘要
     */
    @Override
    public OrgBrief getById(Long id) {
        Org org = orgDao.findById(id);
        if (org == null) {
            throw new BizException(ErrorCode.ORG_NOT_FOUND, "部门不存在: " + id);
        }
        return new OrgBrief(org.getId(), org.getName());
    }

    /**
     * 查询部门树，组装并校验 parentId 必须存在于同表。
     *
     * @return 部门树节点列表
     */
    @Override
    public List<OrgNode> tree() {
        List<Org> orgs = orgDao.findAll();
        validateParentExists(orgs);
        Map<Long, List<Org>> childrenByParent = orgs.stream()
            .filter(org -> org.getParentId() != null)
            .collect(Collectors.groupingBy(Org::getParentId));
        return orgs.stream()
            .filter(org -> org.getParentId() == null)
            .map(org -> toNode(org, childrenByParent))
            .toList();
    }

    /**
     * 校验每个非根节点的 parentId 都存在于同表，非法时抛部门树结构异常。
     *
     * @param orgs 全部部门行
     */
    private void validateParentExists(List<Org> orgs) {
        Set<Long> ids = new HashSet<>();
        orgs.forEach(org -> ids.add(org.getId()));
        for (Org org : orgs) {
            Long parentId = org.getParentId();
            if (parentId != null && !ids.contains(parentId)) {
                throw new BizException(ErrorCode.ORG_TREE_INVALID, "部门树结构非法: 父部门不存在 " + parentId);
            }
        }
    }

    /**
     * 递归把部门行转换为树节点。
     *
     * @param org              当前节点行
     * @param childrenByParent 父主键到子节点列表的索引
     * @return 树节点
     */
    private OrgNode toNode(Org org, Map<Long, List<Org>> childrenByParent) {
        List<OrgNode> children = childrenByParent.getOrDefault(org.getId(), List.of()).stream()
            .map(child -> toNode(child, childrenByParent))
            .toList();
        return new OrgNode(org.getId(), org.getName(), children);
    }
}

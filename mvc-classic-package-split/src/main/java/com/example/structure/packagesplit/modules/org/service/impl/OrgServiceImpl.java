package com.example.structure.packagesplit.modules.org.service.impl;

import com.example.structure.packagesplit.common.constant.ErrorCode;
import com.example.structure.packagesplit.common.exception.BizException;
import com.example.structure.packagesplit.modules.org.dao.OrgDao;
import com.example.structure.packagesplit.modules.org.entity.Org;
import com.example.structure.packagesplit.modules.org.model.OrgTreeModel;
import com.example.structure.packagesplit.modules.org.service.OrgService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门服务实现：在 service 内组装树并校验 parentId 必须存在于同表。
 */
@Service
@RequiredArgsConstructor
public class OrgServiceImpl implements OrgService {

    private final OrgDao orgDao;

    @Override
    public List<OrgTreeModel> tree() {
        List<Org> orgs = orgDao.findAll();
        validateParentExists(orgs);
        Map<Long, List<Org>> childrenByParent = orgs.stream()
            .filter(org -> org.getParentId() != null)
            .collect(Collectors.groupingBy(Org::getParentId));
        return orgs.stream()
            .filter(org -> org.getParentId() == null)
            .map(org -> toModel(org, childrenByParent))
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
     * 递归把部门行转换为树节点模型。
     *
     * @param org              当前节点行
     * @param childrenByParent 父主键到子节点列表的索引
     * @return 树节点模型
     */
    private OrgTreeModel toModel(Org org, Map<Long, List<Org>> childrenByParent) {
        List<OrgTreeModel> children = childrenByParent.getOrDefault(org.getId(), List.of()).stream()
            .map(child -> toModel(child, childrenByParent))
            .toList();
        return new OrgTreeModel(org.getId(), org.getName(), children);
    }
}

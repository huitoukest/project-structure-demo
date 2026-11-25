package com.example.structure.dddclassicmultimodule.adapter.web.converter;

import com.example.structure.dddclassicmultimodule.api.org.dto.OrgNodeDTO;
import com.example.structure.dddclassicmultimodule.biz.org.api.result.OrgNodeResult;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 部门树结果 → 对外 DTO。
 */
@Component
public class OrgDtoConverter {

    /**
     * 递归转换树节点。
     *
     * @param result 业务结果
     * @return 对外 DTO
     */
    public OrgNodeDTO toDto(OrgNodeResult result) {
        List<OrgNodeDTO> children = result.getChildren().stream()
            .map(this::toDto)
            .toList();
        return new OrgNodeDTO(result.getId(), result.getName(), children);
    }

    /**
     * 转换根节点列表。
     *
     * @param results 业务结果列表
     * @return 对外 DTO 列表
     */
    public List<OrgNodeDTO> toDtoList(List<OrgNodeResult> results) {
        return results.stream()
            .map(this::toDto)
            .toList();
    }
}

package org.tis.tools.abf.module.sys.controller.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.entity.enums.DictType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SysDictRequest extends RestRequest {
    private String guid;
    @NotBlank(message = "业务字典不能为空")
    private String dictKey;
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    @NotNull(message = "类型字典不能为空")
    private DictType dictType;
    @NotBlank(message = "字典名称不能为空")
    private String dictName;
    private String dictDesc;
    private String guidParents;
    private String defaultValue;
    private String fromTable;
    private String useForKey;
    private String useForName;
    private BigDecimal seqNo;
    private String sqlFilter;
    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    private DictFromType fromType;
}

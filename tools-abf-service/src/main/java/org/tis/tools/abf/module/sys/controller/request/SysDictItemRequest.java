package org.tis.tools.abf.module.sys.controller.request;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.core.entity.enums.CommonEnumDeserializer;
import org.tis.tools.core.entity.request.RestRequest;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SysDictItemRequest extends RestRequest {
    private String guid;
    @NotBlank(message = "隶属业务字典不能为空")
    private String guidDict;
    @NotBlank(message = "字典项名称不能为空")
    private String itemName;
    private String itemType;
//    @JSONField(deserializeUsing= CommonEnumDeserializer.class)
    @NotBlank(message = "字典项不能为空")
    private String itemVlaue;
    @NotBlank(message = "实际值不能为空")
    private String sendValue;
    private BigDecimal seqNo;
    private String itemDesc;
}
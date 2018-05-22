package org.tis.tools.abf.module.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.sys.controller.request.SysRunConfigAddRequest;
import org.tis.tools.abf.module.sys.controller.request.SysRunConfigRequest;
import org.tis.tools.abf.module.sys.entity.SysRunConfig;
import org.tis.tools.abf.module.sys.service.ISysRunConfigService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

@RestController
@RequestMapping("/sysConfigs")
@Validated
@Api(description = "系统运行参数表")
public class SysRunConfigController extends BaseController {
    @Autowired
    private ISysRunConfigService iSysRunConfigService;

    /**
     * 新增系统参数
     *
     * @param request
     * @return
     */
    @OperateLog(type = OperateType.ADD, desc = "新增系统参数机构")
    @ApiOperation(value = "新增系统参数机构", notes = "实际参数以下面DataType为准")
    @PostMapping
    public ResultVO add(@RequestBody @Validated SysRunConfigAddRequest request) {
        SysRunConfig sysRunConfig = iSysRunConfigService.createSysRunConfig(request.getGuidApp(), request.getGroupName(),
                request.getKeyName(), request.getValueFrom(), request.getValue(), request.getDescription());
        return ResultVO.success("添加成功", sysRunConfig);
    }

    /**
     * 修改系统参数
     *
     * @param request
     * @return
     */
    @OperateLog(type = OperateType.UPDATE, desc = "修改系统参数机构")
    @ApiOperation(value = "修改系统参数机构", notes = "实际参数以下面DataType为准")
    @PutMapping
    public ResultVO updateConfig(@RequestBody @Validated SysRunConfigRequest request) {
        SysRunConfig sysRunConfig = iSysRunConfigService.editSysRunConfig(request.getGuid(), request.getGuidApp(), request.getGroupName(),
                request.getKeyName(), request.getValueFrom(), request.getValue(), request.getDescription());
        return ResultVO.success("修改成功", sysRunConfig);
    }

    /**
     * 查询系统参数
     *
     * @param page
     * @return List
     */
    @ApiOperation(value = "查询系统参数机构", notes = "分页查询")
    @PostMapping("/list")
    public ResultVO queryConfig(@RequestBody @Validated SmartPage<SysRunConfig> page) {
        return ResultVO.success("查询成功",
                iSysRunConfigService.queryAllSysRunConfig(getPage(page), getCondition(page)));
    }

    /**
     * 删除系统参数
     *
     * @param id
     * @return
     */
    @OperateLog(type = OperateType.DELETE, desc = "系统参数机构")
    @ApiOperation(value = "删除系统参数机构", notes = "根据guid删除对应数据信息")
    @DeleteMapping("/{id}")
    public ResultVO deleteConfig(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        return ResultVO.success("删除成功", iSysRunConfigService.deleteSysRunConfig(id));
    }

    /**
     * 查询系统参数
     *
     * @param id
     * @return List
     */
    @ApiOperation(value = "查询系统参数机构", notes = "根据guid查询，输入则根据guid查询数据信息")
    @GetMapping("/{id}")
    public ResultVO queryConfig(@PathVariable @Validated @NotBlank(message = "ID不能为空") String id) {
        return ResultVO.success("查询成功",
                iSysRunConfigService.queryOneSysRunConfig(id));
    }
}

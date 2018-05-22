package org.tis.tools.abf.module.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tis.tools.abf.module.jnl.annotation.OperateLog;
import org.tis.tools.abf.module.jnl.entity.enums.OperateType;
import org.tis.tools.abf.module.sys.controller.request.SysDictQueryRequest;
import org.tis.tools.abf.module.sys.controller.request.SysDictRequest;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.entity.enums.DictType;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.core.web.controller.BaseController;
import org.tis.tools.core.web.vo.ResultVO;
import org.tis.tools.core.web.vo.SmartPage;

import java.text.ParseException;

@RestController
@RequestMapping("/sysDicts")
@Validated
@Api(description = "业务字典")
public class SysDictController  extends BaseController {
    @Autowired
    private ISysDictService iSysDictService;
    /**
     * 新增业务字典
     * @param request
     * @return
     */
    @OperateLog(type = OperateType.ADD, desc = "新增业务字典")
    @ApiOperation(value = "新增业务字典", notes = "实际参数以下面DataType为准，guid不需要输入")
    @PostMapping
    public ResultVO createSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(request.getDictType());
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(request.getFromType());
        sysDict =  iSysDictService.addDict(sysDict);
        return ResultVO.success("添加成功",sysDict);
    }

    /**
     * 删除业务字典
     * @param id
     * @return
     * @throws SysManagementException
     * @throws ParseException
     */
    @OperateLog(type = OperateType.DELETE, desc = "删除业务字典")
    @ApiOperation(value = "删除业务字典", notes = "根据guid删除对应的数据信息")
    @DeleteMapping("/{id}")
    public ResultVO deleteSysDict(@PathVariable @NotBlank String id) {
        SysDict sysDict = iSysDictService.deleteDict(id);
        return ResultVO.success("删除成功",sysDict);
    }

    /**
     * 修改业务字典
     * @param request
     */
    @OperateLog(type = OperateType.UPDATE, desc = "修改业务字典")
    @ApiOperation(value = "修改业务字典", notes = "根据guid查询查询出需要修改的数据并进行更新，参数参考DataType内容")
    @PutMapping
    public ResultVO updateSysDict(@RequestBody @Validated SysDictRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setGuid(request.getGuid());
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictType(request.getDictType());
        sysDict.setDictName(request.getDictName());
        sysDict.setDictDesc(request.getDictDesc());
        sysDict.setGuidParents(request.getGuidParents());
        sysDict.setDefaultValue(request.getDefaultValue());
        sysDict.setFromTable(request.getFromTable());
        sysDict.setUseForKey(request.getUseForKey());
        sysDict.setUseForName(request.getUseForName());
        sysDict.setSeqno(request.getSeqNo());
        sysDict.setSqlFilter(request.getSqlFilter());
        sysDict.setFromType(request.getFromType());
        iSysDictService.editSysDict(sysDict);
        return ResultVO.success("修改成功",sysDict);
    }

    /**
     * 查询单个业务字典
     * @param id
     * @return List
     */
    @ApiOperation(value = "查询业务字典", notes = "根据guid查询出对应的数据信息")
    @GetMapping("/{id}")
    public ResultVO querySysDict(@PathVariable @Validated  @NotBlank String id) {
        SysDict list = iSysDictService.querySysDictByGuid(id);
        return ResultVO.success("查询成功",list);
    }

    /**
     * 查询所有业务字典
     */
    @ApiOperation(value = "查询所有的业务字典", notes = "不需要参数传入")
    @PostMapping ("/list")
    public ResultVO querySysDictList(@RequestBody @Validated SmartPage<SysDict> page){
        Page<SysDict> sysDict = iSysDictService.querySysDicts(getPage(page),getCondition(page));
        return ResultVO.success("查询成功",sysDict);
    }
    @ApiOperation(value = "dictKey和dictName查询", notes = "只需要传入dictKey跟dictName参数进行筛选")
    @PostMapping("/lists")
    public ResultVO dictKeyOrQuery(@RequestBody @Validated SysDictQueryRequest request) {
        SysDict sysDict = new SysDict();
        sysDict.setDictKey(request.getDictKey());
        sysDict.setDictName(request.getDictName());
        return ResultVO.success("查询成功",iSysDictService.dictKeyQuery(sysDict));
    }
}

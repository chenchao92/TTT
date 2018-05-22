package org.tis.tools.abf.module.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tis.tools.abf.module.sys.dao.SysDictItemMapper;
import org.tis.tools.abf.module.sys.entity.SysDict;
import org.tis.tools.abf.module.sys.entity.SysDictItem;
import org.tis.tools.abf.module.sys.entity.enums.DictFromType;
import org.tis.tools.abf.module.sys.exception.SysManagementException;
import org.tis.tools.abf.module.sys.service.ISysDictItemService;
import org.tis.tools.abf.module.sys.service.ISysDictService;
import org.tis.tools.core.exception.i18.ExceptionCodes;

import java.math.BigDecimal;
import java.util.List;

import static org.tis.tools.core.utils.BasicUtil.wrap;
/**
 * sysDictItem的Service接口实现类
 *
 * @author Auto Generate Tools
 * @date 2018/04/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper,SysDictItem> implements ISysDictItemService {
    @Autowired
    private ISysDictService iSysDictService;
    /**
     * 拷贝新增时，代码前缀
     */
    private static final String CODE_HEAD_COPYFROM = "Copyfrom-";


    @Override
    public SysDictItem addDictItem(String guidDict, String itemName, String itemType, String itemValue, String sendValue, String seqNo, String itemDesc) throws SysManagementException {
        SysDictItem sysDictItem = new SysDictItem();
        if (iSysDictService.queryOneSysDictByGuid(guidDict) == null) {
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDict.COLUMN_GUID, guidDict), guidDict);
        }
        sysDictItem.setGuidDict(guidDict);
        sysDictItem.setItemName(itemName);
        sysDictItem.setItemType(itemType);
        sysDictItem.setItemValue(itemValue);
        sysDictItem.setSendValue(sendValue);
        sysDictItem.setSeqno(new BigDecimal(seqNo));
        sysDictItem.setItemDesc(itemDesc);
        insert(sysDictItem);
        return sysDictItem;
    }

    @Override
    public List<SysDictItem> queryDict(String guidDict) throws SysManagementException {
        EntityWrapper<SysDictItem> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDictItem.COLUMN_GUID_DICT, guidDict);
        List<SysDictItem> sysDictItems = selectList(wrapper);
        return sysDictItems;
    }


    @Override
    public SysDictItem editSysDictItem(String guid, String guidDict, String itemName, String itemType, String itemValue, String sendValue, String seqNo, String itemDesc) throws SysManagementException {
        SysDictItem sysDictItem = new SysDictItem();
        EntityWrapper<SysDictItem> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDictItem.COLUMN_GUID, guid);
        if (selectOne(wrapper) == null) {
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDictItem.COLUMN_GUID, guid), guid);
        } else {
            sysDictItem.setGuidDict(guidDict);
            sysDictItem.setItemName(itemName);
            sysDictItem.setItemType(itemType);
            sysDictItem.setItemValue(itemValue);
            sysDictItem.setSendValue(sendValue);
            sysDictItem.setSeqno(new BigDecimal(seqNo));
            sysDictItem.setItemDesc(itemDesc);
            update(sysDictItem, wrapper);
        }
        return sysDictItem;
    }

    @Override
    public SysDictItem deleteDictItem(String id) throws SysManagementException {
        SysDictItem sysDictItem = new SysDictItem();
        EntityWrapper<SysDictItem> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDictItem.COLUMN_GUID, id);
        sysDictItem = selectOne(wrapper);
        if (sysDictItem == null) {
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDictItem.COLUMN_GUID, id), id);
        }
        delete(wrapper);
        return sysDictItem;
    }

    @Override
    public List<SysDictItem> querySysDictItems(String id) throws SysManagementException {
        EntityWrapper<SysDictItem> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDictItem.COLUMN_GUID_DICT, id);
        List<SysDictItem> sysDictItems = selectList(wrapper);
        return sysDictItems;
    }

    @Override
    public SysDictItem guidQueryOneSysDic(String id) throws SysManagementException {
        EntityWrapper<SysDictItem> wrapper = new EntityWrapper<>();
        wrapper.eq(SysDictItem.COLUMN_GUID, id);
        SysDictItem sysDictItem = selectOne(wrapper);
        if (sysDictItem == null) {
            throw new SysManagementException(
                    ExceptionCodes.NOT_FOUND_WHEN_QUERY,
                    wrap(SysDictItem.COLUMN_GUID, id), id);
        }
        return sysDictItem;
    }

    @Override
    public Page<SysDictItem> querySysDictItemList(Page<SysDictItem> page, Wrapper<SysDictItem> wrapper) throws SysManagementException {
        return selectPage(page,wrapper);
    }
}


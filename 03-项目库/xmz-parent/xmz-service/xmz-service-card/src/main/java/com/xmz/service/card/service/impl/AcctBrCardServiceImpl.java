package com.xmz.service.card.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctBrCard;
import com.xmz.service.card.entity.pojo.AcctCardStore;
import com.xmz.service.card.entity.vo.AcctBrCardQuery;
import com.xmz.service.card.entity.vo.MyAcctBrCard;
import com.xmz.service.card.mapper.AcctBrCardMapper;
import com.xmz.service.card.mapper.AcctCardStoreMapper;
import com.xmz.service.card.service.IAcctBrCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 门店领卡 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
@Service
public class AcctBrCardServiceImpl extends ServiceImpl<AcctBrCardMapper, AcctBrCard> implements IAcctBrCardService {

    @Autowired(required = false)
    AcctBrCardMapper acctBrCardMapper;

    @Autowired(required = false)
    AcctCardStoreMapper acctCardStoreMapper;
    /**
     * 发卡管理分页条件查询
     * @param pageParam //分页参数
     * @param brCardQuery //查询条件
     * @return
     */
    @Override
    public Page<MyAcctBrCard> queryAllABrCard(Page<MyAcctBrCard> pageParam, AcctBrCardQuery brCardQuery) {
        return acctBrCardMapper.queryAllABrCard(pageParam,brCardQuery);
    }

    /**
     * 增加发卡
     * @param data
     */
    @Override
    public boolean add(Map<String, String> data) {
        AcctBrCard acctBrCard = JSONObject.parseObject(data.get("form"),AcctBrCard.class);
        //张数
        String zhangshu = data.get("zhangshu");
        //开始卡号
        String startKaHao= data.get("startKaHao");
        //前缀
        String qianzhui=data.get("qianzhui");
        //总数
        String zongshu=data.get("zongshu");
        //剩余数
        String shengNum=data.get("shengNum");
        Long k;
        if(Long.parseLong(zongshu)>Long.parseLong(shengNum)){
            k=Long.parseLong(startKaHao)+(Integer.parseInt(zongshu)-(Integer.parseInt(shengNum)-Integer.parseInt(zhangshu)));

        }else {
            k=Long.parseLong(startKaHao);
        }

        for(int i=0;i<Integer.parseInt(zhangshu);i++){
            Long j= k+i;
            acctBrCard.setCardNo(qianzhui+j);
            acctBrCard.setCardUuid(null);

            acctBrCardMapper.insert(acctBrCard);
        }
        AcctCardStore acctCardStore=new AcctCardStore();
        //获取卡库的id
        acctCardStore.setCardStoreUuid(acctBrCard.getCardStoreUuid());
        //获取要减去的张数
        acctCardStore.setRemainCardNum(new BigDecimal(zhangshu));
        //减去卡库剩余张数
        acctCardStoreMapper.updateNum(acctCardStore);
        return true;
    }




}

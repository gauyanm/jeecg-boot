package org.jeecg.modules.goods.service;

import org.jeecg.modules.goods.entity.GoodsInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: goods_info
 * @Author: jeecg-boot
 * @Date:   2020-10-24
 * @Version: V1.0
 */
public interface IGoodsInfoService extends IService<GoodsInfo> {

    /**
     *
     * @param goodsInfo
     * @return
     */
    public GoodsInfo insertGoodsInfo(GoodsInfo goodsInfo);

    /**
     *
     * @param goodsInfo
     * @return
     */
    public GoodsInfo updateGoodsInfo(GoodsInfo goodsInfo);
}

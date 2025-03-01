package com.o8o1o5.solid.shop;

import java.util.HashMap;
import java.util.Map;

public class ShopGoodsManager {
    private final Map<Integer, String> shopGoodsMap = new HashMap<>();

    public void addShopGoods(int npcId, String goodsId) {
        shopGoodsMap.put(npcId, goodsId);
    }

    public String getShopGoods(int npcId) {
        return shopGoodsMap.get(npcId);
    }
}

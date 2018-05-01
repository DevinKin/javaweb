package cn.devinkin.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

    /**
     * 计算合计，合计等于所有条目的小计之和
     * @return
     */
    public double getTotal() {
        BigDecimal total = new BigDecimal("0");
        for (CartItem cartItem : map.values()) {
            BigDecimal subtotal = new BigDecimal(cartItem.getSubtotal() + "");
            total = total.add(subtotal);
        }
        return total.doubleValue();
    }

    /**
     * 添加条目到购物车中
     * @param cartItem
     */
    public void add(CartItem cartItem) {
        String nbid = cartItem.getBook().getBid();
        //判断车中是否存在该条目
        if (map.containsKey(nbid)) {
            //返回原条目
            CartItem _cartItem = map.get(cartItem.getBook().getBid());
            //设置原条目数量+1
            _cartItem.setCount(_cartItem.getCount() + 1);
            map.put(cartItem.getBook().getBid(), _cartItem);
        } else {
            map.put(nbid, cartItem);
        }
    }

    /**
     * 清空所有条目
     */
    public void clear() {
        map.clear();

    }

    /**
     * 删除指定条目
     * @param bid
     */
    public void delete(String bid) {
        map.remove(bid);
    }

    /**
     * 获取所有条目
     * @return
     */
    public Collection<CartItem> getCartItems() {
        return map.values();
    }
}

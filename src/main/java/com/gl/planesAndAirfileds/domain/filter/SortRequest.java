package com.gl.planesAndAirfileds.domain.filter;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortRequest implements Serializable
{
   private List<OrderRequest> orders;

   public SortRequest()
   {
   }

   public SortRequest(List<OrderRequest> orders)
   {
      this.orders = orders;
   }

   public List<OrderRequest> getOrders()
   {
      return orders;
   }

   public void setOrders(List<OrderRequest> orders)
   {
      this.orders = orders;
   }

   public Sort toSort() {
      if(CollectionUtils.isNotEmpty(orders))
      {
         List<Sort.Order> o = new ArrayList<>();
         for (OrderRequest order : orders) {
            o.add(order.toOrder());
         }
         return new Sort(o);
      }
      return null;
   }
}

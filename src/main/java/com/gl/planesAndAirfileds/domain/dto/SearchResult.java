package com.gl.planesAndAirfileds.domain.dto;

import com.gl.planesAndAirfileds.domain.filter.PagingRequest;

import java.io.Serializable;
import java.util.List;

public class SearchResult<T extends Serializable> implements Serializable
{
   private final List<T> entities;

   private final long count;

   private final PagingRequest pagingRequest;

   public SearchResult(List<T> entities, long count, PagingRequest pagingRequest)
   {
      this.entities = entities;
      this.count = count;
      this.pagingRequest = pagingRequest;
   }

   public List<T> getEntities()
   {
      return entities;
   }

   public long getCount()
   {
      return count;
   }

   public PagingRequest getPagingRequest()
   {
      return pagingRequest;
   }
}

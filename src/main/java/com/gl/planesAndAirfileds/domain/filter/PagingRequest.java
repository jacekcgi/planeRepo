package com.gl.planesAndAirfileds.domain.filter;

import java.io.Serializable;

public class PagingRequest implements Serializable
{
   public static final int DEFAULT_RESULTS_PER_PAGE = 25;

   private int page;

   private int size;

   private SortRequest sort;

   public PagingRequest()
   {
   }

   public PagingRequest(int page, int size, SortRequest sort)
   {
      this.page = page;
      this.size = size;
      this.sort = sort;
   }

   public PagingRequest(int page)
   {
      this(page, DEFAULT_RESULTS_PER_PAGE, null);
   }

   public PagingRequest(int page, int size)
   {
      this(page, size, null);
   }

   public PagingRequest(int offset, SortRequest sort)
   {
      this(offset, DEFAULT_RESULTS_PER_PAGE, sort);
   }

   public int getPage()
   {
      return page;
   }

   public void setPage(int page)
   {
      this.page = page;
   }

   public int getSize()
   {
      return size;
   }

   public void setSize(int size)
   {
      this.size = size;
   }

   public SortRequest getSort()
   {
      return sort;
   }

   public void setSort(SortRequest sort)
   {
      this.sort = sort;
   }
}

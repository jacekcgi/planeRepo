package com.gl.planesAndAirfileds.domain.filter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class SearchRequest<F extends Filter> implements Serializable
{
   private PagingRequest pageRequest = new PagingRequest(0, 1);
//   private PageRequest pageRequest;// = new PageRequest(0, 1);

   private F filter;

   public F getFilter()
   {
      return filter;
   }

   public void setFilter(F filter)
   {
      this.filter = filter;
   }

   public PagingRequest getPageRequest()
   {
      return pageRequest;
   }

   public void setPageRequest(PagingRequest pageRequest)
   {
      this.pageRequest = pageRequest;
   }

   public PageRequest toPageRequest()
   {
      Sort sort = this.pageRequest.getSort().toSort();
      return new PageRequest(this.pageRequest.getPage(), this.pageRequest.getSize(), sort);
   }

//   public PageRequest getPageRequest()
//   {
//      return pageRequest;
//   }
//
//   public void setPageRequest(PageRequest pageRequest)
//   {
//      this.pageRequest = pageRequest;
//   }
}

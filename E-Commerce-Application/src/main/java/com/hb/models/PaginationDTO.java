package com.hb.models;

import lombok.Data;

@Data
public class PaginationDTO {
   private int pageNumber;
   private int pageSize;
   private String name;
   private String sortBy;
   private boolean direction;
 
}

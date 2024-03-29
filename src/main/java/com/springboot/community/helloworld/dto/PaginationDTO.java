package com.springboot.community.helloworld.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer totalPage;
    private Integer page;

    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage=0;
        this.page=page;
        if(totalCount % size==0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount/size+1;
        }
        this.totalPage=totalPage;
        pages.add(page);
        for(int i = 1;i<=3;i++){
            if(page -i>0){
                pages.add(0,page-i);
            }
            if(page + i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否显示上一页按钮
        if(page ==1){
            showNext = false;
        }else{
            showPrevious = true;
        }
        //是否显示下一页按钮
        if(page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示首页按钮
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否展示首页按钮
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }
}

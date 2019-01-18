package com.runoob.test;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vivi
 */
public class Node{
    public String keyword; 
    public String youtubeURL;
    public String title;
    public String pic;
    public int view;
    public String fbURL;
    public String fbcontext;
    public Node parent;
    public int c=0;
    private List<Node> childrenItems;
    public Node(){
    this.keyword=keyword;
    this.youtubeURL=youtubeURL;
    this.title=title;
    this.pic=pic;
    this.view=view;
    this.fbURL = fbURL;
    this.fbcontext=fbcontext;
    
}

    public Node(String youtubeURL,String title,String pic,int view,String keyword){
    this.youtubeURL=youtubeURL;
    this.title=title;
    this.pic=pic;
    this.view=view;
    this.keyword=keyword;
    
     }
    public Node(String fbURL, String fbcontext, Node parent) {
        this.fbURL = fbURL;
        this.fbcontext = fbcontext;
        this.parent=parent;
    }
    public List<Node> getChildrenItems() {
        if (this.c==1){return childrenItems;
        }else{return null;}
        
    }
    public void setChildrenItems(List<Node> childrenItems) {
        this.c=1;
        this.childrenItems = childrenItems;
    }
    public int getChildrensize(){
        return this.childrenItems.size();
    }
    

public String getTitle(){return this.title;} 
public String getPic(){return this.pic;} 
public String getView(){return "觀看次數: "+this.view+" 次";} 
public String getKeyword(){return this.keyword;}
public String getfbURL(){return this.fbURL;}
public String getfbContext(){return this.fbcontext;}
public String getURL(){return this.youtubeURL;}
public Node getparent(){return this.parent;}
public String toString(){
   return "["+youtubeURL+","+title+","+ pic+","+view+"]"+"\n";
}
@Override
public boolean equals(Object obj) {
if(obj == null) return false;
if(this == obj) return true;
if(obj instanceof Node){ 
Node user =(Node)obj;
//			if(user.id = this.id) return true; // 只比較id
// 比較id和username 一致時才返回true 之後再去比較 hashCode
if(user.youtubeURL == this.youtubeURL && user.title.equals(this.title)) return true;
}
return false;
}
/**
* 重寫hashcode 方法，返回的hashCode 不一樣才認定為不同的物件
*/
@Override
public int hashCode() {
//		return id.hashCode(); // 只比較id，id一樣就不新增進集合
return youtubeURL.hashCode() * title.hashCode();
}
}


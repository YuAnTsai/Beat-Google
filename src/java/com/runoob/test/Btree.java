/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runoob.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vivi
 */
public class Btree {
   public Node root;
   public void BuildTree(Node root){
       this.root=root;
   }
   public void PrintTree(){
       System.out.println(root.youtubeURL);
     if(root.getChildrenItems()!=null){
       List<Node>getchildren=root.getChildrenItems();
if(!getchildren.isEmpty()){
       for(int i=0;i<getchildren.size();i++){
        Node n=getchildren.get(i);
        System.out.println(n.fbURL);
if(n.getChildrenItems()!=null){
        List<Node>getFBchildren=n.getChildrenItems();
        if(!getFBchildren.isEmpty()){
        for(int j=0;j<getFBchildren.size();j++){
            Node fb=getFBchildren.get(j);
            System.out.println(fb.fbURL);
        }
       
       }}}}}
      /* System.out.println(this.root.youtubeURL);
       try{
       List<Node>getchildren=this.root.getChildrenItems();
       for(int i=0;i<getchildren.size();i++){
        Node n=getchildren.get(i);
        System.out.println(n.fbURL);
        List<Node>getFBchildren=n.getChildrenItems();
        for(int j=0;j<getFBchildren.size();j++){
            Node fb=getchildren.get(j);
            System.out.println(fb.fbURL); 
        }
       }}catch(Exception ex){
           
       }
*/  
   }
  
}

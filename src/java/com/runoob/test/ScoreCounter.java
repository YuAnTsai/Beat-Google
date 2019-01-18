package com.runoob.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vivi
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vivi
 */
public class ScoreCounter {
	
	private ArrayList<DefaultWords> weightWords = new ArrayList<DefaultWords>(Arrays.asList(
			new DefaultWords("dog",10), new DefaultWords("funniest",5), new DefaultWords("hilarious",5),
			new DefaultWords("laugh",5), new DefaultWords("tease",4), new DefaultWords("fail",3),
			new DefaultWords("husky",5), new DefaultWords("corgi",5), new DefaultWords("ultimate",2),
			new DefaultWords("compilation",2),new DefaultWords("golden retriever",5), new DefaultWords("pug",5),
			new DefaultWords("狗",10), new DefaultWords("好笑",5), new DefaultWords("搞笑",5),
			new DefaultWords("笑",5), new DefaultWords("逗",4), new DefaultWords("失誤",3),
			new DefaultWords("哈士奇",5), new DefaultWords("柯基",5),new DefaultWords("柴犬",5),new DefaultWords("合集",2),
                        new DefaultWords("黃金獵犬",5),new DefaultWords("巴哥",5)
			
			));
	
	
    
    public ScoreCounter(Node node,String ky) {
   
    weightWords.add(new DefaultWords(ky,10)); 
    count(node);
    }
    
    public int count(Node node){
    	String nodeTitle = node.title;
        int nodeview=node.view;
    	nodeTitle = nodeTitle.toLowerCase();
    	
    	int sum = 0;
    	for(int i  = 0; i < weightWords.size(); i++){
    		String str = weightWords.get(i).dWord;
    		int weight = weightWords.get(i).weight;
    		
    		if(nodeTitle.indexOf(str) != -1){
    			sum = sum + weight;
    		}
    		
    	}
    if(((node.view)>10000)&&((node.view)<100000)){ 
        sum=sum+1;
    }
      else if(((node.view)>100000)&&((node.view)<1000000)){
            sum=sum+2;
        }else if(((node.view)>1000000)&&((node.view)<10000000)){
            sum=sum+3;
        }else if(((node.view)>10000000)&&((node.view)<100000000)){
            sum=sum+4;
        }else if((node.view)>100000000){
        sum=sum+5;
    }
    	return sum;
    }
    
}



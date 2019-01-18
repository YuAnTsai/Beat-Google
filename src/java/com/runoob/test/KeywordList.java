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
import java.io.PrintWriter;
import java.util.*;
public class KeywordList {
public ArrayList<Node> list;
public int count=0;
public boolean see=true;
public KeywordList() {
	this.list=new ArrayList<Node>();
}
public void add(Node yt) {
ScoreCounter ScoreCounter = new ScoreCounter(yt,yt.keyword);

            for (int i = 0; i < list.size(); i++) {
		Node keyword = list.get(i);
		ScoreCounter ScoreCounter2 = new ScoreCounter(keyword,yt.keyword);
	if(keyword.title.equals(yt.title)){
                    see=false;
            break;
        }
                
                
                  if (ScoreCounter2.count(keyword) < ScoreCounter.count(yt)) {
			this.list.add(i, yt);
			return;
		}else if (ScoreCounter2.count(keyword) == ScoreCounter.count(yt)) {
			if (keyword.view < yt.view) {  		
			this.list.add(i, yt);
			return;
			}
		}
                }
		if(see==true){
		this.list.add(yt);
                }
	}

public void output() {
      
	StringBuilder sb = new StringBuilder();
	for(int i =0;i <this.list.size();i++) {
		Node keyword = this.list.get(i);
		
		if(i>0) {
			sb.append(" ");
		}
		sb.append(keyword.toString());
	}
		System.out.println(sb.toString());
}
public Node get(int i){return this.list.get(i);}

public int size(){return this.list.size();}

public ArrayList<Node> getList(){return this.list;}
}



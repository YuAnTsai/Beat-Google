/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runoob.test;

/**
 *
 * @author Vivi
 */
public class DefaultWords {
	public String dWord;
	public int weight;
	
	public DefaultWords(String dWord, int weight) {
		this.dWord = dWord;
		this.weight = weight;
	}
	
	public String toString(){
		return "[" + dWord + "," + weight + "]";
	}

}

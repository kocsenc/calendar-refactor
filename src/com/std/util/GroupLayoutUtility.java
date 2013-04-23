package com.std.util;

import java.awt.Component;

import javax.swing.GroupLayout;

/**
 * Utility class for using GroupLayout
 * 
 * @author xxx
 *
 */
public class GroupLayoutUtility {
	
	/**
	 * creates a new array of parallel groups
	 * 
	 * @param layout the group layout to use
	 * @param length the length of the array to make
	 * @return a new array of parallel groups
	 */
	private static GroupLayout.ParallelGroup[] createNewParallelGroups(GroupLayout layout, int length) {
		GroupLayout.ParallelGroup[] ret = new GroupLayout.ParallelGroup[length];
		for(int i = 0; i < length; i++)
			ret[i] = layout.createParallelGroup();
		return ret;
	}

	/**
	 * creates a new array of sequential groups
	 * 
	 * @param layout the group layout to use
	 * @param length the length of the array to make
	 * @return a new array of sequential groups
	 */
	private static GroupLayout.SequentialGroup createNewSeqGroup(GroupLayout layout, GroupLayout.ParallelGroup[] parallel) {
		GroupLayout.SequentialGroup ret = layout.createSequentialGroup();
		for(GroupLayout.ParallelGroup group : parallel)
			ret.addGroup(group);
		return ret;
	}
	
	/**
	 * Adds a 2-dimensional array of components to a GroupLayout 
	 * according to their position in the array
	 * 
	 * @param layout the group layout to use
	 * @param aac components to add
	 */
	public static void addToGroups(GroupLayout layout, Component[][] aac) {
		GroupLayout.ParallelGroup[] h = createNewParallelGroups(layout, aac.length);
		GroupLayout.ParallelGroup[] v = createNewParallelGroups(layout, aac[0].length);
		
		for(int i = 0; i < aac.length; i++) {
			for(int j = 0; j < aac[0].length; j++) {
				h[i].addComponent(aac[i][j]);
				v[j].addComponent(aac[i][j]);
			}
		}
		
		layout.setHorizontalGroup(createNewSeqGroup(layout, v));
		layout.setVerticalGroup(createNewSeqGroup(layout, h));
	}
}

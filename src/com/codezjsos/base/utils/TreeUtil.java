package com.codezjsos.base.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeUtil {

	public static List<Tree> exchange(String topId, List<Tree> trees){

		
		List<Tree> all = new ArrayList<Tree>();

		Set<Tree> handleTrees = new HashSet<TreeUtil.Tree>();
		
		List<Tree> rootTrees = new ArrayList<Tree>();
		
		for (Tree tree : trees) {
			
			boolean handle = false;
			/**
			 * 
			 * 把顶级节点的子节点储存找出来。
			 */
			if(topId.equals(tree.getId())){
				rootTrees.add(tree);
				handle = true;
			}
			all.add(tree);
			
			if(handle){
				handleTrees.add(tree);
			}
		}
		/**
		 * 
		 * 设置子节点
		 */
		for(Tree tree: rootTrees){

			List<Tree> childrens = new ArrayList<TreeUtil.Tree>();
			childrens = getChildrens(tree.getId(), all, handleTrees);
			tree.setChildrens(childrens);
		}
		
		return rootTrees;
	}
	
	private static List<Tree> getChildrens(String pid, List<Tree> all, Set<Tree> handleTrees){
		
		List<Tree> childrens = new ArrayList<TreeUtil.Tree>();
		if(all.size()>handleTrees.size()){
			for(Tree tree: all){
				if(tree.getPid().equals(pid)){
					childrens.add(tree);
					handleTrees.add(tree);
				}
			}
			for(Tree tree:childrens){
				tree.setChildrens(getChildrens(tree.getId(), all, handleTrees));
			}
		}
		return childrens;
	}
	
	public static abstract class AdapterTree<E>{
		
		public abstract List<Tree> exchange(List<E> source);
	}
	
	public static class Tree {
		private String id;

		private String name;

		private String pid;

		private List<Tree> childrens;

		private Object attr;
		
		
		public Tree(String id, String name, String pid, List<Tree> childrens,
				Object attr) {
			super();
			this.id = id;
			this.name = name;
			this.pid = pid;
			this.childrens = childrens;
			this.attr = attr;
		}

		public Object getAttr() {
			return attr;
		}

		public void setAttr(Object attr) {
			this.attr = attr;
		}

		public void setPid(String pid) {
			this.pid = pid;
		}

		public Tree(String id,String name,String pid){
			this.id = id;
			this.name = name;
			this.pid = pid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPid() {
			return pid;
		}

		public void setpid(String pid) {
			this.pid = pid;
		}

		public List<Tree> getChildrens() {
			return childrens;
		}

		public void setChildrens(List<Tree> childrens) {
			this.childrens = childrens;
		}



		@Override
		public String toString() {
			return "Tree [id=" + id + ", name=" + name + ", pid=" + pid
					+ ", childrens=" + childrens + "]";
		}

	}

	public static void main(String[] args) {
		
//		Tree tree1 = new Tree(1, "顶层节点1", 0);
//		Tree tree2 = new Tree(2, "顶层节点2", 0);
//		Tree tree3 = new Tree(3, "顶层节点3", 0);
//
//		Tree tree4 = new Tree(4, "二级节点4", 1);
//		Tree tree5 = new Tree(5, "二级节点5", 2);
//		Tree tree6 = new Tree(6, "二级节点6", 3);
//
//		Tree tree7 = new Tree(7, "三级节点7", 4);
//		Tree tree8 = new Tree(8, "三级节点8", 4);
//		Tree tree9 = new Tree(9, "三级节点9", 5);
//
//		List<Tree> trees = new ArrayList<Tree>();
//		trees.add(tree9);
//		trees.add(tree8);
//		trees.add(tree7);
//		trees.add(tree6);
//		trees.add(tree5);
//		trees.add(tree4);
//		trees.add(tree3);
//		trees.add(tree2);
//		trees.add(tree1);
//
//		List<Tree> rootTrees = exchangeexchange(trees);
//
//		for (Tree tree : rootTrees) {
//			System.out.println(tree.toString());
//		}

	}

}

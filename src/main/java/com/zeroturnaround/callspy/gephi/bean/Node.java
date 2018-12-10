package com.zeroturnaround.callspy.gephi.bean;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private static int nodeCount = 0;

    private int id;
    private String lable;
    private Node parent;
    private List<Node> children = new LinkedList<>();
    private int stackHeight;

    public Node(String lable) {
        id = nodeCount ++;
        this.lable = modifyLable(lable);
    }

    public Node(String lable, Node parent) {
        id = nodeCount ++;
        this.lable = modifyLable(lable);
        this.parent = parent;
    }

    private String modifyLable(String lable) {
        return lable.trim().replace("<", "").replace(">", "");
    }

    public int getStackHeight() {
        return stackHeight;
    }

    public Node setStackHeight(int stackHeight) {
        this.stackHeight = stackHeight;
        return this;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public String getLable() {
        return lable;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public int getId() {
        return id;
    }
}

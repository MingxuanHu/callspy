package com.zeroturnaround.callspy.gephi.bean;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private static int nodeCount = 0;

    private int id;
    private String label;
    private Node parent;
    private List<Node> children = new LinkedList<>();
    private int stackHeight;
    private String clazz;

    public Node(String label) {
        id = nodeCount ++;
        this.label = modifyLable(label);
        this.clazz = getClazz(label);
    }

    public Node(String label, Node parent) {
        id = nodeCount ++;
        this.label = modifyLable(label);
        this.parent = parent;
        this.clazz = getClazz(label);
    }

    private String getClazz(String lable) {
        try {
            String[] strs = lable.split(":")[0].replaceAll("\\(.*\\)", "").split("\\.");
            return strs[strs.length - 2];
        } catch (Exception e) {
            return lable;
        }
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

    public String getLabel() {
        return label;
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

    public String getClazz() {
        return clazz;
    }
}

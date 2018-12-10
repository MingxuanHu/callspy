package com.zeroturnaround.callspy.gephi;

import com.zeroturnaround.callspy.Stack;
import com.zeroturnaround.callspy.gephi.bean.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GephiGenerator {
    private static int edgeCount = 0;

    public static void generateGephiXml(String inputFile, String outputFile) throws Exception {
        Node root;
        // the node at the ith index represents the node to which the program will return to if the method returns
        List<Node> currentNode4EachStackHeight = new ArrayList<>(20);

        try (
                FileReader fileReader = new FileReader(inputFile);
                PrintWriter printWriter = new PrintWriter(outputFile)
        ) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }

            root = new Node(line, null);
            root.setStackHeight(getHeight(line));
            currentNode4EachStackHeight.add(root.getStackHeight(), root);

            while ((line = bufferedReader.readLine()) != null) {
                Node node = new Node(line);
                node.setStackHeight(getHeight(line));
                node.setParent(currentNode4EachStackHeight.get(node.getStackHeight() - 1));
                currentNode4EachStackHeight.get(node.getStackHeight() - 1).addChild(node);
                currentNode4EachStackHeight.add(node.getStackHeight(), node);
            }
            generateGephiXml(root, printWriter);
        }
    }

    private static int getHeight(String line) {
        int i = 0;
        while (line.startsWith(Stack.SPACE)) {
            i ++;
            line = line.substring(1);
        }
        return i;
    }

    private static void printTree(Node node) {
        String preFix = "";
        for (int i = 0; i < node.getStackHeight(); i ++) {
            preFix += Stack.SPACE;
        }
        System.out.println(preFix + node.getLable());
        for (Node child : node.getChildren())
            printTree(child);
    }

    private static void generateGephiXml(Node node, PrintWriter printWriter) {
        StringBuffer nodeBuffer = new StringBuffer();
        StringBuffer edgeBuffer = new StringBuffer();
        getNodeAndEdge(node, null, nodeBuffer, edgeBuffer);
        printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<gexf xmlns=\"http://www.gexf.net/1.2draft\" version=\"1.2\">" +
                    "<graph mode=\"static\" defaultedgetype=\"directed\">" +
                        "<nodes>" +
                            nodeBuffer +
                        "</nodes>" +
                        "<edges>" +
                            edgeBuffer +
                        "</edges>" +
                    "</graph>" +
                "</gexf>");
    }

    private static void getNodeAndEdge(Node thisNode, Node previousNode, StringBuffer nodeBuffer, StringBuffer edgeBuffer) {
        nodeBuffer.append("<node id=\"").append(thisNode.getId()).append("\" label=\"").append(thisNode.getLable()).append("\" />");
        if (thisNode.getParent() != null) {
            edgeBuffer.append("<edge id=\"").append(edgeCount).append("\" source=\"")
                    .append(thisNode.getParent().getId()).append("\" target=\"").append(thisNode.getId())
                    .append("\" label=\"").append(edgeCount++).append("\" weight=\"1\" />");
        }
        for (Node child : thisNode.getChildren())
            getNodeAndEdge(child, thisNode, nodeBuffer, edgeBuffer);
        // skip the return edges for clarity
//        if (previousNode != null) {
//            edgeBuffer.append("<edge id=\"").append(edgeCount).append("\" source=\"")
//                    .append(thisNode.getId()).append("\" target=\"").append(previousNode.getId())
//                    .append("\" label=\"").append(edgeCount++).append("\" weight=\"1\" />");
//        }
    }
}

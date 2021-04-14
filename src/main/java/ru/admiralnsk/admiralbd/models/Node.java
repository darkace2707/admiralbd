package ru.admiralnsk.admiralbd.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {

    private String nodeId; // node id
    private String pid; // parent id
    private String text;
    private String href;

    public Node(String nodeId, String pId, String text, String href) {
        this.nodeId = nodeId;
        this.pid = pId;
        this.text = text;
        this.href = href;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId='" + nodeId + '\'' +
                ", pid='" + pid + '\'' +
                ", text='" + text + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
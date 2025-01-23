/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pengelolafile;

import java.util.LinkedList;

/**
 *
 * @author ACER
 */
public class TreeNode {
    private String name;
    private boolean isFolder;
    private String content;
    private LinkedList<TreeNode> children;

    public TreeNode(String name, boolean isFolder) {
        this.name = name;
        this.isFolder = isFolder;
        this.children = new LinkedList<>();
        if (!isFolder) {
            this.content = "";
        }
    }

    public String getName() {
        return name;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LinkedList<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}



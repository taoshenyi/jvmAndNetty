package com.unionfin.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTree
{
    public static void main(String[] args)
    {
        TestData data = new TestData();
        Map<String, TreeNode> midStr = new HashMap<String, TreeNode>();
        for (int i = 0; i < data.listTreeNode.size(); i++)
        {
            midStr.put(data.listTreeNode.get(i).getId(),
                    data.listTreeNode.get(i));
        }
        String root = null; // 用来保存根节点的id
        for (Map.Entry<String, TreeNode> entry : midStr.entrySet())
        {
            TreeNode t = midStr.get(entry.getValue().getParentId());
            if (t != null)
                midStr.get(entry.getValue().getParentId()).getChildren()
                        .add(entry.getValue());
            else
                root = entry.getKey();
        }
        System.out.println(midStr.get(root));
        TreeNode ret = midStr.get(root);
        System.out.println("\\");
    }

    static class TreeNode
    {
        private String id;
        private String parentId;
        private List<TreeNode> children = new ArrayList<TreeNode>();


        public TreeNode(String p_id, String p_parentId)
        {
            this.id = p_id;
            this.parentId = p_parentId;
        }


        public String getId()
        {
            return id;
        }


        public void setId(String id)
        {
            this.id = id;
        }


        public String getParentId()
        {
            return parentId;
        }


        public void setParentId(String parentId)
        {
            this.parentId = parentId;
        }


        public List<TreeNode> getChildren()
        {
            return children;
        }


        public void setChildren(List<TreeNode> children)
        {
            this.children = children;
        }

    }

    static class TestData
    {
        public List<TreeNode> listTreeNode = new ArrayList<TreeNode>();


        TestData()
        {
            listTreeNode.add(new TreeNode("1", "0"));

            listTreeNode.add(new TreeNode("7", "3"));

            listTreeNode.add(new TreeNode("2", "1"));

            listTreeNode.add(new TreeNode("3", "1"));

            listTreeNode.add(new TreeNode("4", "2"));

            listTreeNode.add(new TreeNode("5", "2"));

            listTreeNode.add(new TreeNode("6", "1"));
        }
    }
}

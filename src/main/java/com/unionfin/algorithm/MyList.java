package com.unionfin.algorithm;

public class MyList<T>
{
    // 定义一个内部类作为节点
    private class Node
    {
        private Node next;
        private T data;


        // 无参构造方法
        public Node()
        {

        }


        public Node(T data, Node next)
        {
            this.next = next;
            this.data = data;
        }
    }

    // 头结点
    private Node header;
    // 尾节点
    private Node tail;
    // 数目
    private int size;


    public MyList()
    {
        header = null;
        tail = null;
    }


    // 获取链表的长度
    public int getSize()
    {
        return size;
    }


    // 根据index获取链表节点

    public Node getNodeByIndex(int index)
    {
        if (index < 0 || index > size - 1)
        {
            throw new IndexOutOfBoundsException("index 越界");
        }
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next)
        {
            if (index == i)
            {
                return current;
            }
        }
        return null;
    }


    // 获取data
    public T get(int index)
    {
        return this.getNodeByIndex(index).data;
    }


    // 在头结点插入数据
    public void addHead(T element)
    {
        Node current = new Node(element, null);
        current.next = header;
        header = current;
        if (tail == null)
        {
            tail = current;
        }
        size++;
    }


    // 在尾部插入节点
    public void add(T element)
    {
        if (header == null)
        {
            header = new Node(element, null);
            tail = header;
        }
        else
        {
            Node current = new Node(element, null);
            tail.next = current;
            tail = current;
        }
        size++;
    }


    public T delete(int index)
    {

        // 删除头结点
        if (index < 0 || index > size - 1)
        {
            throw new IndexOutOfBoundsException("index 越界");
        }
        Node del = null;
        // 删除头结点
        if (index == 0)
        {
            del = header;
            header.next = header;
        }
        else if (index == size - 1)
        {
            // 获取前一个节点
            Node prev = this.getNodeByIndex(index - 1);
            // 删除的是尾节点
            del = prev.next;
            prev.next = null;
            tail = prev;
        }
        else
        {
            Node prev = this.getNodeByIndex(index - 1);
            // 获取待删除的节点
            del = prev.next;
            // 将prev.next指向del.next
            prev.next = del.next;
            del.next = null;

        }
        size--;
        return del.data;
    }

}

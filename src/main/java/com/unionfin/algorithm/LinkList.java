package com.unionfin.algorithm;

public class LinkList<T>
{
    // 定义一个内部类Node,代表链表的节点
    private class Node
    {
        private T data;// 保存数据
        private Node next;// 指向下个节点的引用


        // 无参构造器
        public Node()
        {

        }


        // 初始化全部属性的构造器
        public Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }

    private Node header;// 保存头结点
    private Node tail;// 保存尾节点

    private int size;// 保存已含有的节点数


    // 创建空链表
    public LinkList()
    {
        header = null;
        tail = null;
    }


    // 已指定元素创建链表，只有一个元素

    public LinkList(T element)
    {
        header = new Node(element, null);
        tail = header;
        size++;
    }


    // 返回链表的长度
    public int length()
    {
        return size;
    }


    // 获取指定索引处的元素
    public T get(int index)
    {
        return this.getNodeByIndex(index).data;
    }


    // 获取指定位置的节点
    private Node getNodeByIndex(int index)
    {
        if (index < 0 || index > size - 1)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        Node current = header;

        for (int i = 0; i < size && current != null; i++, current = current.next)
        {
            if (i == index)
            {
                return current;
            }
        }
        return null;
    }


    // 按值查找所在w位置
    public int locate(T element)
    {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next)
        {
            if (current.data.equals(element))
            {
                return i;
            }
        }
        return -1;
    }


    // 在尾部插入元素
    public void add(T element)
    {
        // 如果链表是空的
        if (header == null)
        {
            header = new Node(element, null);
            // 只有一个节点，header，tail都指向该节点
            tail = header;
        }
        else
        {
            Node newNode = new Node(element, null);
            // 将尾节点的next指向新的节点
            tail.next = newNode;
            // 将新节点设置为尾节点
            tail = newNode;
        }
        size++;
    }


    // 在头部添加元素
    public void addHead(T element)
    {
        // 创建新节点，让新节点的next指向header
        Node newNode = new Node(element, null);
        newNode.next = header;
        header = newNode;

        if (tail == null)
        {
            tail = header;
        }
        size++;
    }


    // 在指定位置插入元素
    public void insert(T element, int index)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        if (header == null)
        {
            add(element);
        }
        else
        {
            // 插入的index是0，表明将该节点设置为头结点
            if (index == 0)
            {
                addHead(element);
            }
            else
            {
                // 插入的不是头结点，也不是从尾部插入的,这个时候就要获取前一个节点
                Node prev = this.getNodeByIndex(index - 1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }


    // 删除指定索引处的元素

    public T delete(int index)
    {
        if (index < 0 || index > size - 1)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        Node del = null;
        // 如果删除的是头结点
        if (index == 0)
        {
            del = header;
            header = del.next;// 将头节点的下一个节点设置为头结点
        }
        else if (index == size - 1)
        {
            Node prev = this.getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            tail = prev;
        }
        else
        {
            Node prev = this.getNodeByIndex(index - 1);

            // 提取待删除的节点

            del = prev.next;

            // 将待删除的节点的next节点指向prev.next节点
            prev.next = del.next;
            del.next = null;
        }
        size--;
        return del.data;
    }


    // 删除最后一个元素
    public T remove()
    {
        return delete(size - 1);
    }


    // 判断线性表是否为空
    public boolean isEmpty()
    {
        return size == 0;
    }


    // 清空线性表
    public void clear()
    {
        // 将header,tail置为null
        header = null;
        tail = null;
        size = 0;
    }


    public String toString()
    {

        if (isEmpty())
        {
            return "[]";
        }
        else
        {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next)
            {
                sb.append(current.data.toString() + ", ");
            }

            int len = sb.length();

            return sb.delete(len - 2, len).append("]").toString();
        }

    }
}

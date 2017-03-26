package com.unionfin.algorithm;

public class MyDoubleLinkList<T>
{
    private volatile int length = 0;
    private Node head;
    private Node tail;


    public MyDoubleLinkList()
    {
        tail = head = null;// 头结点和尾节点都指向一个空的节点，这个空的节点的prev,next都为null
    }


    public int length()
    {
        return length;
    }


    // 获取指定位置的数据元素
    public T get(int index)
    {
        return this.getNodeByIndex(index).data;
    }


    // 获取指定位置的节点
    private Node getNodeByIndex(int index)
    {
        if (index < 0 || index > length - 1)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }

        if (index < length / 2)
        {
            Node current = head;
            for (int i = 0; i < length / 2 && current != null; i++, current = current.next)
            {
                if (i == index)
                {
                    return current;
                }
            }
        }
        else
        {
            Node current = tail;
            for (int i = length - 1; i >= length / 2 && current != null; i--, current = current.prev)
            {
                if (i == index)
                {
                    return current;
                }
            }
        }
        return null;
    }


    // 按值查询所在的位置
    public int locate(T element)
    {
        Node current = head;

        for (int i = 0; i < length - 1 && current != null; i++, current = current.next)
        {
            if (element.equals(current.data))
            {
                return i;
            }
        }

        return -1;
    }


    // 采用尾部插入的方法添加新的节点

    public void add(T element)
    {
        // 若还是空的表，则将header和tail 都指向这个元素
        if (head == null)
        {
            head = new Node(element, null, null);
            tail = head;
        }
        else
        {
            // 创建新的节点
            Node current = new Node(element, tail, null);
            tail.next = current;
            tail = current;
        }
        length++;
    }


    // 采用头部插入的方式添加节点
    public void addHead(T element)
    {
        Node current = new Node(element, null, head);
        head.prev = current;
        head = current;
        if (tail == null)
        {
            tail = head;
        }
        length++;
    }


    // 向指定位置插入元素
    public void insert(T element, int index)
    {
        if (index < 0 || index > length)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        if (head == null)
        {
            this.add(element);
        }
        else
        {
            if (index == 0)
            {
                this.addHead(element);
            }
            else
            {
                // 前一个节点
                Node prev = this.getNodeByIndex(index - 1);
                // 后一个节点
                Node next = prev.next;
                // 插入的节点
                Node current = new Node(element, prev, next);
                prev.next = current;
                next.prev = current;
                length++;
            }
        }
    }


    // 删除指定索引处的元素
    public T delete(int index)
    {
        if (index < 0 || index > length - 1)
        {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        Node del = null;
        if (index == 0)
        {
            del = head;
            head = head.next;
            head.prev = null;// 断开引用
        }
        else if (index == length - 1)// 删除的是最后一个节点
        {
            Node prev = tail.prev; // 获取前一个节点
            del = tail;
            del.prev = null;
            prev.next = null; // 前一个节点的next设置为空
            tail = prev;

        }
        else
        {
            Node prev = this.getNodeByIndex(index - 1);
            del = prev.next;// 待删除的节点

            Node next = del.next;
            // 让之前的节点指向下一个节点
            prev.next = next;
            next.prev = prev;
            del.prev = null;
            del.next = null;

        }
        return del.data;
    }


    // 判断是否为空
    public boolean isEmpty()
    {
        return length == 0;
    }


    // 清空线性表
    public void clear()
    {
        head = null;
        tail = null;
        length = 0;
    }

    private class Node
    {
        private Node prev;// 指针域前驱
        private Node next;// 指针域后继
        private T data;// 数据域


        public Node()
        {
        }


        // 双向链表的后继以及数据域
        public Node(T data, Node prev, Node next)
        {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }


        public Node getPrev()
        {
            return prev;
        }


        public void setPrev(Node prev)
        {
            this.prev = prev;
        }


        public Node getNext()
        {
            return next;
        }


        public void setNext(Node next)
        {
            this.next = next;
        }

    }
}

package com.zheng.stack;

import java.util.Arrays;

/**
 * Created by zhenghui on 2018/10/29.
 * 栈的实现 后进先出
 */
public class TestStack {

    public static void main(String args[]) {
        ArrayStack<Integer> s = new ArrayStack<Integer>();
        s.push(56);
        s.push(6785);
        s.push(342);
        System.out.println("start-->" + s.size);
        System.out.println("栈顶元素为" + s.pop());// 打印 342 System.out.println("栈顶元素为"+s.pop());//打印 6785
        System.out.println("end-->" + s.size);
    }
}


/**
 * 数组实现栈
 * 主要有push pop peek empty
 * push 放入元素
 * pop 从栈中取出栈顶元素
 * peek 查看堆栈顶部对象
 */
class ArrayStack<E> {
    int size;//数组中存储元素的个数
    private Object[] stack;

    public ArrayStack() {
        stack = new Object[10];
    }

    public boolean empty() {
        return size == 0;
    }

    public E push(E item) {
        stack[size++] = item;
        return item;
    }

    //取出栈顶元素 移除对象并返回对象
    public synchronized E pop() {
        ensureCapacity(size + 1);//往数组中添加元素的时候 先进行容量检测 如果已经放满了 那么就再+10个容量
        E e = peek();//获取栈顶元素
        stack[size - 1] = null;
        size--;
        return e;
    }

    //查看栈顶元素
    public synchronized E peek() {
        if (empty()) {
            return null;
        }
        return (E) stack[size - 1];
    }

    //放满了，容量不够就扩容
    public void ensureCapacity(int size) {
        int len = stack.length;
        if (len > size) {
            int newLen = 10;
            stack = Arrays.copyOf(stack, newLen);
        }
    }
}

//链表LinkedList实现栈
class Node<E> {
    Node<E> next = null;
    E data;

    public Node(E data) {
    }
}

class LinkedStack<E> {
    Node<E> top = null;//头结点

    public boolean empty() {
        return top == null;
    }

    public void push(E data) {
        Node<E> newNode = new Node<E>(data);//创建新节点
        newNode.next = top;//将老的栈顶元素放到第二的位置
        top = newNode;//新的结点指向单链表的头结点
    }

    public E pop() {
        if (this.empty()) {
            return null;
        }

        E data = top.data;//pop时候将链表的头结点指向next
        top = top.next;//将next作为新的头结点即可
        return data;
    }

    public E peek() {
        if (this.empty()) {
            return null;
        }
        E data = top.data;
        return data;
    }

}
package com.zheng.jvm;

/**
 * Created by zhenghui on 2018/10/23.
 * 申请内存及jvm各代使用情况
 * -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=2
 *
 * 堆大小
 * -Xms 初始堆大小，默认为物理内存的1/64(<1GB)
 * -Xmx 最大堆大小，默认(MaxHeapFreeRatio参数可以调整)空余堆内存大于70%时，JVM会减少堆直到 -Xms的最小限制
 * 年轻代
 * -XX:NewSize 新生代空间大小初始值
 * -XX:MaxNewSize 新生代空间大小最大值
 * -Xmn 新生代空间大小，此处的大小是(eden+2 survivor space)
 * 年老代
 * a.老年代的空间大小会根据新生代的大小隐式设定
 * b.初始值=-Xmx(堆)减去-XX:NewSize(最小)的值
 * c.最小值=-Xmx(堆)值减去-XX:MaxNewSize(最大)的值
 * 永久代：1.8一下，存放Class和Meta的信息
 * -XX  永久代空间的值
 * -XX:PermSize 永久代空间的初始值&最小值
 * -XX:MaxPermSize 永久代空间的最大值
 *
 * 元空间 1.8后替代永久代，存放Class和Meta的信息
 * -XX:MaxMetaspaceSize 元空间的值
 *
 * -XX:SurvivorRatio=2: Survivor有两个，比值为2，所以是2:1:1为整个年轻代的大小，Eden=(10/4)*2M
 *
 *  Q：Survivor为什么是两个？
 *  A：主要是用来解决内存碎片化(不连续内存)，如果不连续意味堆内可放入的内处对象就少。
 *  Eden满后触发PSYoungGen GC，将存活对象移动到survivor space S0，Eden（from space）被清空，
 *  等Eden区第二次满了，再触发PSYoungGen GC，Eden和S0中的存活对象被复制送入第二块survivor space S1（to space）,Eden和S0被清空。
 *  如此循环往复。如果对象的复制次数达到16次，该对象就会被送到老年代中。
 *
 * 为什么移除持久代(Class+Meta)？
 * 空间值启动时固定(-XX:MaxPermSize)，调优困难
 * 简化Full GC：每一个回收器有专门的元数据迭代器
 * 不暂停GC的情况下并发地释放类数据。
 * 改进持久代对未来开放
 */
public class MaxTenuringThreshold {
    public static void main(String args[]){
        byte[] b1,b2,b3,b4,b5;
//        b5= new byte[1024*512];
        b1 = new byte[1024*512];//申请512K
        b2 = new byte[1024*1024*1];//申请2M
        b3 = new byte[1024*1024*1];//申请2M
        b4 = new byte[1024*1024*1];
        b5 = new byte[1024*1024*2];
        //定义的byte[]对象，null对象也会占用PSYoungGen空间
        //-XX:SurvivorRatio=2时 Survivor有两个，比值为2，所以是2:1:1为整个年轻代的大小，Eden = (10/4)*2M, S0 = S1 = 2.5M
    }
}

package com.datastructure;

/**
 * Note: 加减乘除运算优先级 > 移位运算 > 位运算(&、|) > 逻辑运算(&&、||)
 *
 * @author zhangqiang
 * @since 2021/4/12 10:48 下午
 */
public class BitMap {

    // 用来存储数字
    private final int[] elems;

    private final int size;

    /**
     * 使用 int 数组来存储
     *
     * @param size 数组的大小 size = max_number / 32 + 1
     */
    public BitMap(int size) {
        this.size = size;
        elems = new int[size];
    }

    /**
     * 添加一个数到 BitMap
     *
     * @param num
     */
    public void add(int num) {
        // 数字在数组的下标
        int index = num / 32;
        // 数字在 32 位整数的偏移量。
        // 32 二进制为 100000
        // 31 二进制为 011111
        // num & 31 == num % 32
        int offset = num & 31;
        // 将对应位，置为 1
        elems[index] |= 1 << offset;
    }

    /**
     * 删除 BitMap 中的某个数，当对应位为 1 时，设置为 0
     *
     * @param num
     */
    public void delete(int num) {
        int index = num / 32;
        int offset = num & 31;
        System.out.println("num = " + num);
        System.out.println("offset = " + offset);
        System.out.println("Integer.toBinaryString(elems[index]) = " + Integer.toBinaryString(elems[index]));
        System.out.println("1 << offset = " + Integer.toBinaryString(1 << offset));
        System.out.println("elems[index] & 1 << offset = " + (elems[index] & 1 << offset));
        // Note: 如果对应位为 1，& 之后对应位还是 1，不管对应位是第几位，最终结果都会大于 0，而如果对应位为 0，则结果一定是 0
        if ((elems[index] & 1 << offset) != 0) {
            // 异或，不同为真(1)，相同为假(0)，1 ^ 1 = 0
            elems[index] ^= 1 << offset;
        }
    }

    /**
     * 判断某个数是否已经存在
     *
     * @param num
     * @return
     */
    public boolean isExists(int num) {
        // 数字在数组的下标
        int index = num / 32;
        // 数字在 32 位整数的偏移量。 num & 31 == num % 32
        int offset = num & 31;
        // 移位运算优先级大于位运算(&、|)，将对应位
        // 移到最右端再做计算
        // 也可以用 (elems[index] & 1 << offset) != 0，如果对应位为 1，& 之后对应位还是 1，
        // 不管对应位是第几位，最终结果都会大于 0，而如果对应位为 0，则结果一定是 0
        return (elems[index] >> offset & 0x01) == 1;
    }

    @Override
    public String toString() {
        StringBuilder binaryString = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            StringBuilder elemBinary = new StringBuilder(Integer.toBinaryString(elems[i]));
            binaryString.append(intBinary32(elemBinary)).append(",");
        }
        return binaryString.substring(0, binaryString.length() - 1);
    }

    /**
     * 将 int 类型的 binary string 不足 32 位的高位补零
     *
     * @param binaryString
     * @return
     */
    public StringBuilder intBinary32(StringBuilder binaryString) {
        while (binaryString.length() < 32) {
            binaryString.insert(0, "0");
        }
        return binaryString;
    }

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(2);
        bitMap.add(0);
        System.out.println("bitMap.add(0) " + bitMap.toString());
        bitMap.add(1);
        System.out.println("bitMap.add(1) " + bitMap.toString());
        bitMap.add(2);
        System.out.println("bitMap.add(2) " + bitMap.toString());
        bitMap.delete(1);
        System.out.println("bitMap.delete(1) " + bitMap.toString());
    }

}

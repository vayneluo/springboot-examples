package com.xiaoluo.boot.integrate.leetcode.day01;

import com.xiaoluo.boot.integrate.leetcode.LogUtil;
import org.junit.Test;

/**
 * @classname: ReverseInteger
 * @description: 逆序整数
 * @author: Vayne.Luo
 * @date 2021/1/6 10:38
 */
public class ReverseInteger {

    @Test
    public void test(){
        LogUtil.output(reverse(123));
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 示例 1:
     * 输入: 123
     * 输出: 321
     *
     * 示例 2:
     * 输入: -123
     * 输出: -321
     *
     * 示例 3:
     * 输入: 120ProxyInvocationHandler
     * 输出: 21
     * 注意:
     *
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为[−231, 231− 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     */
    public int reverse(int x){
        long res = 0;
        for (; x != 0 ; x /= 10) {
            res = res * 10 + x % 10;
        }
        return res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int)res;
    }

}

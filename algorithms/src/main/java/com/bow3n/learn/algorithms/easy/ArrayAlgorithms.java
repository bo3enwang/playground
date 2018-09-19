package com.bow3n.learn.algorithms.easy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.print.Pageable;
import java.util.*;

public class ArrayAlgorithms {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 1;
        int current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != current) {
                nums[index] = nums[i];
                current = nums[i];
                index++;
            }
        }
        return index;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int count = 0;
        int max = 0;
        int buyIn = 0;

        for (int i = 1; i < prices.length; i++) {
            int earn = prices[i] - prices[buyIn];
            if (earn > max) {
                max = earn;
                if (i == prices.length - 1) {
                    count += earn;
                }
            } else {
                count += max;
                max = 0;
                buyIn = i;
            }
        }
        return count;
    }

    public static void rotate(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[0];
        int cnt = 0;
        while (cnt++ < length) {
            i = (i + k) % length;
            int t = nums[i];
            nums[i] = cur;
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public static boolean containsDuplicate_a(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            s.add(num);
        }
        return nums.length != s.size();
    }

    public void rotate1(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) {
            return;
        }
        int length = nums.length;
        int start = 0;
        int i = 0;
        int cur = nums[0];
        for (int j = 0; j < length; j++) {
            i = (i + k) % length;
            int t = nums[i];
            nums[i] = cur;
            if (i == start) {
                ++start;
                ++i;
                cur = nums[i];
            } else {
                cur = t;
            }
        }
    }

    public boolean containsDuplicate_b(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_containsDuplicate() {
        Assertions.assertTrue(containsDuplicate_a(new int[]{1, 2, 3, 1}));
        Assertions.assertTrue(containsDuplicate_a(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        Assertions.assertFalse(containsDuplicate_a(new int[]{1, 2, 3, 4}));

        Assertions.assertTrue(containsDuplicate_b(new int[]{1, 2, 3, 1}));
        Assertions.assertTrue(containsDuplicate_b(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        Assertions.assertFalse(containsDuplicate_b(new int[]{1, 2, 3, 4}));
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums 非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次
     * @return 只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || i == nums.length - 1) {
                if (i == 0 && nums[0] != nums[1]) {
                    return nums[0];
                }
                if (i == nums.length - 1 && nums[nums.length - 1] != nums[nums.length - 2]) {
                    return nums[nums.length - 1];
                }
            } else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }
        return nums[0];
    }

    public int singleNumber_a(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int aA : A) {
            rst ^= aA;
        }
        return rst;
    }

    @Test
    public void test_singleNumber() {
        Assertions.assertEquals(1, singleNumber(new int[]{2, 2, 1}));
        Assertions.assertEquals(4, singleNumber(new int[]{4, 1, 2, 1, 2}));
        Assertions.assertEquals(4, singleNumber_a(new int[]{4, 1, 2, 1, 2}));
    }


    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int num1Index = 0;
        int num2Index = 0;
        int length = Math.min(nums1.length, nums2.length);
        int intersectArrIndex = 0;
        int[] intersectArr = new int[length];

        while (num1Index < nums1.length && num2Index < nums2.length) {
            if (nums1[num1Index] == nums2[num2Index]) {
                intersectArr[intersectArrIndex++] = nums1[num1Index];
                num1Index++;
                num2Index++;
            } else if (nums1[num1Index] < nums2[num2Index]) {
                num1Index++;
            } else {
                num2Index++;
            }
        }
        return Arrays.copyOf(intersectArr, intersectArrIndex);
    }


    @Test
    public void test_intersect() {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        Assertions.assertEquals(new int[]{2, 2}, intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
//        Assertions.assertEquals(new int[]{4, 9}, intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4}));
    }


    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * @param digits 整数组成的非空数组
     * @return 在该数的基础上加一
     */
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        boolean loop = true;
        while (loop) {
            int newValue = digits[i] + 1;
            if (newValue == 10) {
                if (i == 0) {
                    int[] newArr = new int[digits.length + 1];
                    Arrays.fill(newArr, 0);
                    newArr[0] = 1;
                    return newArr;
                }
                digits[i--] = 0;
            } else {
                digits[i--] = newValue;
                loop = false;
            }
        }
        return digits;
    }


    @Test
    public void test_plusOne() {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
        System.out.println(Arrays.toString(plusOne(new int[]{1})));
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     */
    public void moveZeroes(int[] nums) {
        int endIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                for (int j = i; j < endIndex; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                endIndex--;
            }
        }
    }

    @Test
    public void test_moveZeroes() {
//        int[] nums = new int[]{0, 1, 2, 0, 3};
        int[] nums = new int[]{0, 0, 1};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     * <p>
     * stupid
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
//        Arrays.sort(nums);
        int[] positions = new int[2];
        int firstIndex = 0;
        int secondIndex = 1;
        boolean secondLast = false;
        while (firstIndex < nums.length - 1) {
            int count = nums[firstIndex] + nums[secondIndex];
            if (count == target) {
                positions[0] = firstIndex;
                positions[1] = secondIndex;
                return positions;
            } else {
                if (secondIndex++ == nums.length - 1) {
                    secondLast = true;
                }
            }
            if (secondLast) {
                firstIndex++;
                secondIndex = firstIndex + 1;
                secondLast = false;
            }
        }
        return null;
    }


    public int[] twoSum_a(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!numMap.containsKey(nums[i])) {
                numMap.put(nums[i], i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int subtract = target - nums[i];
            Integer otherIndex = numMap.get(subtract);
            if (otherIndex != null && otherIndex != i) {
                return new int[]{i, numMap.get(subtract)};
            }
        }
        return null;
    }


    @Test
    public void test_twoSum() {
        System.out.println(Arrays.toString(twoSum_a(new int[]{3, 2, 4}, 6)));
//        System.out.println(Arrays.toString(twoSum(new int[]{1, 3, 2, 3, 3, 6}, 9)));
    }


    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> colSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                int rowNum = board[i][j];
                if (rowNum != 46) {
                    if (rowSet.contains(rowNum)) {
                        return false;
                    } else {
                        rowSet.add(rowNum);
                    }
                }
                int colNum = board[j][i];
                if (colNum != 46) {
                    if (colSet.contains(colNum)) {
                        return false;
                    } else {
                        colSet.add(colNum);
                    }
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col) {
        Set<Integer> set = new HashSet<>();
        int rowLength = row + 3;
        int colLength = col + 3;
        for (int i = row; i < rowLength; i++) {
            for (int j = col; j < colLength; j++) {
                int num = board[i][j];
                if (num != 46) {
                    if (set.contains(num)) {
                        return false;
                    } else {
                        set.add(num);
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void test_isValidSudoku() {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board));


        char[][] board1 = new char[][]{
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(isValidSudoku(board1));
    }


    public void rotate(int[][] matrix) {
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j < length - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - j - 1][i];
                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                matrix[j][length - i - 1] = temp;
            }
        }


    }


    @Test
    public void test_rotate() {
        int[][] matrix =
                {
                        {5, 1, 9, 11},
                        {2, 4, 8, 10},
                        {3, 3, 6, 7},
                        {5, 14, 12, 16}
                };
        rotate(matrix);

        System.out.println(Arrays.deepToString(matrix));
    }


}
package com.rsharipov.codingtasks;

public class MedianOfTwoSortedArrays {

    double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1.length + nums2.length) % 2 == 0) {
            return (findIthElement(nums1, nums2, (nums1.length + nums2.length) / 2 - 1) +
                findIthElement(nums1, nums2, (nums1.length + nums2.length) / 2)) / 2.0;
        }
        return findIthElement(nums1, nums2, (nums1.length + nums2.length) / 2);
    }
    
    int findIthElement(int[] nums1, int[] nums2, int index) {
        int result = findIthElementOfFirst(nums1, nums2, index);
        if (result != -1) {
            return nums1[result];
        }
        return nums2[findIthElementOfFirst(nums2, nums1, index)];        
    }
    
    int findIthElementOfFirst(int[] nums1, int[] nums2, int index) {
        int begin = 0;
        int end = Math.min(nums1.length, index + 1);
        while (begin + 1 < end) {
            int mid = (begin + end) / 2;
            int relationToPosition = shouldGo(nums1, nums2, mid, index);
            if (relationToPosition == 0) return mid;
            if (relationToPosition < 0) {
                end = mid;
            }
            else {
                begin = mid;
            }
        }
        if (shouldGo(nums1, nums2, begin, index) != 0) {
            return -1;
        }
        return begin;
    }
    
    int shouldGo(int[] nums1, int[] nums2, int indexInFirst, int indexOverall) {
        if (indexInFirst >= nums1.length) return -1;
        final int leftInFirst = indexInFirst;
        final int leftInRight = indexOverall - leftInFirst;
        if (leftInRight > nums2.length) return 1;
        if (leftInRight > 0 && nums1[indexInFirst] < nums2[leftInRight - 1]) return 1;
        if (leftInRight < nums2.length && nums1[indexInFirst] > nums2[leftInRight]) return -1;
        return 0;
    }
};

class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int total = 0;

        int maxSum = nums[0];
        int currentmax = 0;

        int minSum = nums[0];
        int currentmin = 0;

        for (int num : nums) {

            total += num;

            // Maximum subarray
            currentmax = Math.max(currentmax + num, num);
            maxSum = Math.max(maxSum, currentmax);

            // Minimum subarray
            currentmin = Math.min(currentmin + num, num);
            minSum = Math.min(minSum, currentmin);
        }

        // All numbers are negative
        if (maxSum < 0) {
            return maxSum;
        }

        return Math.max(maxSum, total - minSum);
    }
}
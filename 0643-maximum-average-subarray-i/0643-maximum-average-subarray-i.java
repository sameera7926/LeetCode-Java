class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int left = 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            if (right - left + 1 > k) {
                sum -= nums[left];
                left++;
            }

            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return (double) maxSum / k;
    }
}
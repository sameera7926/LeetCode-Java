class Solution {
    public int pivotIndex(int[] nums) {

        int leftsum = 0;
        int totalsum = 0;

        // 1. Calculate total sum
        for(int i = 0; i < nums.length; i++) {
            totalsum = totalsum + nums[i];
        }

        // 2. Check every index
        for(int i = 0; i < nums.length; i++) {

            int rightsum = totalsum - leftsum - nums[i];

            if(leftsum == rightsum) {
                return i;
            }

            leftsum = leftsum + nums[i];
        }

        return -1;
    }
}
class Solution {
    public int maxAbsoluteSum(int[] nums) {
      int maxsum=0;
      int minsum=0;
      int ans = 0;
      for(int i=0;i<nums.length;i++){
        maxsum = Math.max(maxsum+nums[i],0);
          minsum = Math.min(minsum+nums[i],0);
          ans = Math.max(ans,Math.max(maxsum,-minsum));
      } 
      return ans; 
    }
}
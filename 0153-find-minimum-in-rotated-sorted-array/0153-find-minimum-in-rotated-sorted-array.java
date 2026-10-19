class Solution {
public int findMin(int[] nums) {

    int low = 0;
    int right = nums.length - 1;
    int res = nums[0];

    while (low <= right) {

        if (nums[low] <= nums[right]) {
            res = Math.min(res, nums[low]);
            break;
        }

        int mid = low + (right - low) / 2;

        res = Math.min(res, nums[mid]);

        if (nums[mid] >= nums[low]) {
            low = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return res;
}

}

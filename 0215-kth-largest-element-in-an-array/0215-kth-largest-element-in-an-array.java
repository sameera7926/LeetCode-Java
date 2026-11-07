class Solution {
    public int findKthLargest(int[] nums, int k) {

        // Create Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Insert first K elements
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }

        // Process remaining elements
        for (int i = k; i < nums.length; i++) {

            // If current element is smaller than or equal
            // to the smallest element in the heap, ignore it
            if (nums[i] <= pq.peek()) {
                continue;
            }

            // Remove smallest element
            pq.poll();

            // Insert current element
            pq.offer(nums[i]);
        }

        // Root contains Kth largest element
        return pq.peek();
    }
}
class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            ans[i] = -1;
        }

        for (int i = 2 * n - 1; i >= 0; i--) {

            int current = nums[i % n];

            while (!st.isEmpty() && st.peek() <= current) {
                st.pop();
            }

            if (i < n) {
                if (!st.isEmpty()) {
                    ans[i] = st.peek();
                }
            }

            st.push(current);
        }

        return ans;
    }
}
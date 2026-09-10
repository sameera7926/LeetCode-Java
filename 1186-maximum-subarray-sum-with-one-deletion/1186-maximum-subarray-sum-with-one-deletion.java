class Solution {
    public int maximumSum(int[] arr) {

        int nodelete = arr[0];
        int onedelete = Integer.MIN_VALUE;
        int ans = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int prevNodelete = nodelete;
            int prevOnedelete = onedelete;

            nodelete = Math.max(prevNodelete + arr[i], arr[i]);

            int v2;

            if (prevOnedelete == Integer.MIN_VALUE) {
                v2 = prevNodelete;
            } else {
                v2 = Math.max(prevOnedelete + arr[i], prevNodelete);
            }

            onedelete = v2;

            ans = Math.max(ans, Math.max(onedelete, nodelete));
        }

        return ans;
    }
}
import java.util.HashMap;

class Solution {
    public int totalFruit(int[] fruits) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int ans = 0;

        for (int right = 0; right < fruits.length; right++) {

            // Add the current fruit into the window
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            // If there are more than 2 fruit types,
            // shrink the window from the left
            while (map.size() > 2) {

                map.put(fruits[left], map.get(fruits[left]) - 1);

                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            // Window is valid (contains at most 2 fruit types)
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
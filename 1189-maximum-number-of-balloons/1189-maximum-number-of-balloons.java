class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] arr = new int[26];

        for (int i = 0; i < text.length(); i++) {
            arr[text.charAt(i) - 'a']++;
        }

        return Math.min(
            Math.min(
                Math.min(arr['b' - 'a'], arr['a' - 'a']),
                Math.min(arr['l' - 'a'] / 2, arr['o' - 'a'] / 2)
            ),
            arr['n' - 'a']
        );
    }
}
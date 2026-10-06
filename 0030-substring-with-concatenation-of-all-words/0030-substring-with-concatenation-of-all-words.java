class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<>();

        HashMap<String, Integer> wordCount = new HashMap<>();
        HashMap<String, Integer> windowCount = new HashMap<>();

        int k = words[0].length();
        int totalLength = words.length * k;

        // Count required words
        for (int i = 0; i < words.length; i++) {
            wordCount.put(
                words[i],
                wordCount.getOrDefault(words[i], 0) + 1
            );
        }

        // Try each possible starting offset
        for (int offset = 0; offset < k; offset++) {

            int left = offset;
            int right = offset;
            int count = 0;

            windowCount.clear();

            while (right + k <= s.length()) {

                String word = s.substring(right, right + k);
                right += k;

                // If word is required
                if (wordCount.containsKey(word)) {

                    windowCount.put(
                        word,
                        windowCount.getOrDefault(word, 0) + 1
                    );

                    count++;

                    // Too many occurrences of this word
                    while (windowCount.get(word) > wordCount.get(word)) {

                        String leftWord = s.substring(left, left + k);
                        left += k;

                        windowCount.put(
                            leftWord,
                            windowCount.get(leftWord) - 1
                        );

                        count--;
                    }

                    // All words found
                    if (count == words.length) {
                        result.add(left);

                        String leftWord = s.substring(left, left + k);
                        left += k;

                        windowCount.put(
                            leftWord,
                            windowCount.get(leftWord) - 1
                        );

                        count--;
                    }

                } else {
                    // Word is not required
                    windowCount.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}